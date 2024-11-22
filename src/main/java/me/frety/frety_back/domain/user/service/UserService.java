package me.frety.frety_back.domain.user.service;

import lombok.RequiredArgsConstructor;
import me.frety.frety_back.domain.account.entity.Account;
import me.frety.frety_back.domain.account.repository.AccountRepository;
import me.frety.frety_back.domain.account.request.CreateAccount;
import me.frety.frety_back.domain.account.usecase.AccountUseCase;
import me.frety.frety_back.domain.auth.entity.AuthenticationType;
import me.frety.frety_back.domain.auth.request.AddAuthenticationRequest;
import me.frety.frety_back.domain.auth.usecase.AuthUseCase;
import me.frety.frety_back.domain.user.request.CreateUserRequest;
import me.frety.frety_back.domain.user.usecase.UserUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static me.frety.frety_back.domain.account.entity.Role.USER;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService implements UserUseCase {
    private final AccountUseCase accountUseCase;
    private final AuthUseCase authUseCase;

    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public Long createUser(CreateUserRequest request) {
        /**
         * 계정 생성
         */
        CreateAccount createAccount = CreateAccount.builder()
                .role(USER)
                .name(request.getName())
                .build();
        Long accountId = accountUseCase.createAccount(createAccount);

        /**
         * 계정 조회
         */
        Account account = accountRepository.findById(accountId)
                .filter(a -> !a.isDeleted())
                .orElseThrow(() -> new RuntimeException("계정을 찾을 수 없습니다."));

        /**
         * 인증방식 생성
         */
        AuthenticationType authenticationType = switch (request.getMethod()) {
            case ID_PASSWORD -> AuthenticationType.ID_PASSWORD;
        };
        AddAuthenticationRequest addAuthenticationRequest = AddAuthenticationRequest.builder()
                .type(authenticationType)
                .accountId(account.getId())
                .loginId(request.getLoginId())
                .password(request.getPassword())
                .build();
        authUseCase.addAuthentication(addAuthenticationRequest);

        return account.getId();
    }
}
