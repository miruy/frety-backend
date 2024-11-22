package me.frety.frety_back.domain.account.service;

import lombok.RequiredArgsConstructor;
import me.frety.frety_back.domain.account.entity.Account;
import me.frety.frety_back.domain.account.entity.Privacy;
import me.frety.frety_back.domain.account.repository.AccountRepository;
import me.frety.frety_back.domain.account.request.CreateAccount;
import me.frety.frety_back.domain.account.usecase.AccountUseCase;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService implements AccountUseCase {
    private final AccountRepository accountRepository;


    @Override
    public Long createAccount(CreateAccount request) {
        /**
         * 계정 생성
         */
        Account account = Account.builder()
                .role(request.getRole())
                .build();

        /**
         * 개인정보 생성
         */
        Privacy privacy = Privacy.builder()
                .name(request.getName())
                .build();

        /**
         * 계정과 개인정보 연결
         */
        account.connectPrivacy(privacy);

        /**
         * 계정 저장
         */
        accountRepository.save(account);

        return account.getId();
    }
}
