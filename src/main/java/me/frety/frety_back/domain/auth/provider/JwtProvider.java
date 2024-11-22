//package me.frety.frety_back.domain.auth.provider;
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import lombok.extern.slf4j.Slf4j;
//import me.frety.frety_back.domain.auth.entity.Authentication;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//@Slf4j
//@Component
//public class JwtProvider {
//
//    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;            // 30분
//    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;  // 7일
//
//    private final Key key;
//
//    public JwtProvider(@Value("${jwt.secret}") String secretKey) {
//        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
//        this.key = Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    // Access Token 생성
//    public String generateAccessToken(Authentication authentication) {
//        long now = (new Date()).getTime();
//        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
//
//        String accessToken = Jwts.builder()
//                .setSubject(authentication.getLoginId())
//                .claim("auth", authentication.getAccount().getAuthorities())
//                .setExpiration(accessTokenExpiresIn)
//                .signWith(key, SignatureAlgorithm.HS512)
//                .compact();
//
//        return accessToken;
//    }
//
//    // Refresh Token 생성
//    public String generateRefreshToken(Authentication authentication) {
//        long now = (new Date()).getTime();
//        Date RefreshTokenExpiresIn = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);
//
//        String refreshToken = Jwts.builder()
//                .setSubject(authentication.getLoginId())
//                .claim("auth", authentication.getAccount().getAuthorities())
//                .setExpiration(RefreshTokenExpiresIn)
//                .signWith(key, SignatureAlgorithm.HS512)
//                .compact();
//
//        return refreshToken;
//    }
//
//    public Authentication getAuthentication(String accessToken) {
//        // 토큰 복호화
//        Claims claims = parseClaims(accessToken);
//
//        if (claims.get("auth") == null) {
//            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
//        }
//
//        String loginId = claims.getSubject();
//        List<String> authorities = Arrays.asList(claims.get("auth").toString().split(","));
//
//        Authentication authentication = new Authentication();
//
//        authentication.setLoginId(loginId);
//        authentication.setAuthorities(authorities);
//
//        return authentication;
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
//            return true;
//        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
//            log.info("잘못된 JWT 서명입니다.");
//        } catch (ExpiredJwtException e) {
//            log.info("만료된 JWT 토큰입니다.");
//        } catch (UnsupportedJwtException e) {
//            log.info("지원되지 않는 JWT 토큰입니다.");
//        } catch (IllegalArgumentException e) {
//            log.info("JWT 토큰이 잘못되었습니다.");
//        }
//        return false;
//    }
//
//    private Claims parseClaims(String accessToken) {
//        try {
//            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
//        } catch (ExpiredJwtException e) {
//            return e.getClaims();
//        }
//    }
//}