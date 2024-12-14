package me.frety.frety_back.domain.auth.service;

import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import me.frety.frety_back.domain.account.entity.Account;
import me.frety.frety_back.domain.exception.InvalidCredentialsException;
import me.frety.frety_back.domain.account.repository.AccountRepository;
import me.frety.frety_back.domain.auth.request.CreateTokenRequest;
import me.frety.frety_back.domain.auth.entity.Authentication;
import me.frety.frety_back.domain.auth.entity.Token;
import me.frety.frety_back.domain.auth.entity.TokenType;
import me.frety.frety_back.domain.auth.repository.AuthenticationRepository;
import me.frety.frety_back.domain.auth.request.AddAuthenticationRequest;
import me.frety.frety_back.domain.auth.response.CreateTokenResponse;
import me.frety.frety_back.domain.auth.usecase.AuthUseCase;
import me.frety.frety_back.util.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

import static me.frety.frety_back.domain.auth.entity.AuthenticationType.ID_PASSWORD;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService implements AuthUseCase {
    private final AccountRepository accountRepository;
    private final AuthenticationRepository authenticationRepository;

    @Value("${auth.jwt.secret-key}")
    private String secretKey;

    private SecretKey signKey;

    private final JWTUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        this.signKey = jwtUtil.createSecretKey(secretKey);
    }

    @Override
    @Transactional
    public void addAuthentication(AddAuthenticationRequest request) {
        /**
         * 계정 조회
         */
        Account account = accountRepository.findById(request.getAccountId())
                .filter(a -> !a.isDeleted())
                .orElseThrow(() -> new InvalidCredentialsException("계정을 찾을 수 없습니다."));

        /**
         * 인증방식 생성
         */
        Authentication authentication = switch (request.getType()) {
            case ID_PASSWORD -> {
                if (request.getLoginId() == null || request.getPassword() == null) {
                    throw new InvalidCredentialsException("loginId 또는 password가 NULL입니다.");
                }

                if (authenticationRepository.existsByLoginId(request.getLoginId())) {
                    throw new InvalidCredentialsException("중복된 아이디가 존재합니다.");
                }

                yield Authentication.builder()
                        .type(ID_PASSWORD)
                        .loginId(request.getLoginId())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .build();
            }
            default -> throw new RuntimeException("지원하지 않는 인증 방식입니다. 인증방식 추가 실패.");
        };

        /**
         * 인증 방식 추가
         */
        account.addAuthentication(authentication);
    }

    @Override
    @Transactional
    public CreateTokenResponse createToken(CreateTokenRequest request) {
        /**
         * 인증방식 조회
         */
        Authentication authentication = switch (request.getType()) {
            case ID_PASSWORD -> {
                if (request.getLoginId() == null || request.getPassword() == null) {
                    throw new InvalidCredentialsException("loginId 또는 password가 NULL입니다.");
                }

                Authentication _authentication = authenticationRepository
                        .findByTypeAndLoginId(ID_PASSWORD, request.getLoginId())
                        .filter(a -> !a.isDeleted())
                        .orElseThrow(() -> new InvalidCredentialsException("아이디 또는 패스워드가 일치하지 않습니다."));

                if (!passwordEncoder.matches(request.getPassword(), _authentication.getPassword())) {
                    throw new InvalidCredentialsException("아이디 또는 패스워드가 일치하지 않습니다.");
                }

                yield _authentication;
            }
        };

        /**
         * JWT 토큰 생성
         */
        Account account = authentication.getAccount();

        LocalDateTime accessTokenExpiredAt = LocalDateTime.now().plusDays(7);

        Map<String, Object> claims = new HashMap<>();
        claims.put("authenticationId", authentication.getId());
        claims.put("authority", account.getRole());
        claims.put("authorities", List.of(account.getRole()));

        String jwtToken = Jwts.builder()
                .subject(account.getId().toString())
                .claims(claims)
                .issuedAt(new Date())
                .expiration(Date.from(accessTokenExpiredAt.atZone(ZoneOffset.systemDefault()).toInstant()))
                .signWith(signKey)
                .compact();

        /**
         * 토큰 생성
         */
        Token accessToken = Token.builder()
                .type(TokenType.ACCESS_TOKEN)
                .value(jwtToken)
                .expiredAt(accessTokenExpiredAt)
                .build();
        Token refreshToken = Token.builder()
                .type(TokenType.REFRESH_TOKEN)
                .value(UUID.randomUUID().toString())
                .expiredAt(LocalDateTime.now().plusDays(30))
                .build();

        /**
         * 토큰 추가
         */
        authentication.addToken(accessToken);
        authentication.addToken(refreshToken);

        return CreateTokenResponse.builder()
                .accessToken(accessToken.getValue())
                .refreshToken(refreshToken.getValue())
                .authId(authentication.getAccount().getId())
                .build();
    }
}
