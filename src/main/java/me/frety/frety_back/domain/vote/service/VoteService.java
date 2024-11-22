package me.frety.frety_back.domain.vote.service;

import lombok.RequiredArgsConstructor;
import me.frety.frety_back.domain.account.entity.Account;
import me.frety.frety_back.domain.account.repository.AccountRepository;
import me.frety.frety_back.domain.common.entity.BaseEntity;
import me.frety.frety_back.domain.vote.entity.Vote;
import me.frety.frety_back.domain.vote.repository.VoteRepository;
import me.frety.frety_back.domain.vote.request.CreateVoteRequest;
import me.frety.frety_back.domain.vote.usecase.VoteUseCase;
import me.frety.frety_back.domain.tab.entity.Tab;
import me.frety.frety_back.domain.tab.repository.TabRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VoteService implements VoteUseCase {
    private final VoteRepository voteRepository;
    private final TabRepository tabRepository;
    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public Long createVote(CreateVoteRequest request, Long authenticatedAccountId) {

        Account authenticatedAccount = accountRepository.findById(authenticatedAccountId)
                .filter(BaseEntity::isNotDeleted)
                .filter(Account::isUser)
                .orElseThrow(() -> new RuntimeException("인증되지 않은 유저입니다."));

        /**
         * 평가 등록
         */
        Vote vote = Vote.builder()
                .voter(authenticatedAccount)
                .build();

        /**
         * 평가와 targetId 연결
         */
        switch (request.getType()) {
            case TAB -> {
                Tab tab = tabRepository.findById(request.getTargetId())
                        .filter(t -> !t.isDeleted())
                        .orElseThrow(() -> new RuntimeException("악보가 존재하지 않습니다."));

                vote.connectTab(tab);
            }
            default -> throw new RuntimeException("지원되지 않는 타입입니다. 평가 등록 실패");
        }

        /**
         * 평가 저장
         */
        voteRepository.save(vote);

        return vote.getId();
    }
}
