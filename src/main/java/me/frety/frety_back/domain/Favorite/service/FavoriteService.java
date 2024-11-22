package me.frety.frety_back.domain.Favorite.service;

import lombok.RequiredArgsConstructor;
import me.frety.frety_back.domain.Favorite.entity.Favorite;
import me.frety.frety_back.domain.Favorite.repository.FavoriteRepository;
import me.frety.frety_back.domain.Favorite.request.CreateFavoriteRequest;
import me.frety.frety_back.domain.Favorite.usecase.FavoriteUseCase;
import me.frety.frety_back.domain.account.entity.Account;
import me.frety.frety_back.domain.account.repository.AccountRepository;
import me.frety.frety_back.domain.comment.entity.Comment;
import me.frety.frety_back.domain.common.entity.BaseEntity;
import me.frety.frety_back.domain.tab.entity.Tab;
import me.frety.frety_back.domain.tab.repository.TabRepository;
import me.frety.frety_back.domain.vote.entity.Vote;
import me.frety.frety_back.domain.vote.repository.VoteRepository;
import me.frety.frety_back.domain.vote.request.CreateVoteRequest;
import me.frety.frety_back.domain.vote.usecase.VoteUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FavoriteService implements FavoriteUseCase {
    private final FavoriteRepository favoriteRepository;
    private final TabRepository tabRepository;
    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public Long createFavorite(CreateFavoriteRequest request, Long authenticatedAccountId) {

        Account authenticatedAccount = accountRepository.findById(authenticatedAccountId)
                .filter(BaseEntity::isNotDeleted)
                .filter(Account::isUser)
                .orElseThrow(() -> new RuntimeException("인증되지 않은 유저입니다."));

        /**
         * 평가 등록
         */
        Favorite favorite = Favorite.builder()
                .favoriter(authenticatedAccount)
                .build();

        /**
         * 즐겨찾기와 targetId 연결
         */
        switch (request.getType()) {
            case TAB -> {
                Tab tab = tabRepository.findById(request.getTargetId())
                        .filter(t -> !t.isDeleted())
                        .orElseThrow(() -> new RuntimeException("악보가 존재하지 않습니다."));

                // 기존에 삭제된 즐겨찾기를 복구하거나 새로 생성하는 로직
                Favorite existingFavorite = favoriteRepository.findByFavoriterIdAndTabId(authenticatedAccount.getId(), request.getTargetId())
                        .filter(f -> f.isDeleted())  // 삭제된 즐겨찾기가 있으면 복구
                        .orElse(null); // 없다면 null 반환

                if (existingFavorite != null) {
                    // 삭제된 즐겨찾기 복구
                    existingFavorite.recover();  // recover 메서드를 추가해서 소프트 삭제 상태에서 복구
                    favorite = existingFavorite;  // 기존 즐겨찾기 객체를 사용
                } else {
                    // 새로 즐겨찾기 생성
                    favorite.connectTab(tab);
                }

            }
            default -> throw new RuntimeException("지원되지 않는 타입입니다. 평가 등록 실패");
        }

        /**
         * 평가 저장
         */
        favoriteRepository.save(favorite);

        return favorite.getId();
    }

    @Override
    @Transactional
    public void deleteFavorite(Long favoriteId, Long tabId) {
        /**
         * 즐겨찾기 단건 조회 (악보 ID와 계정 ID로 확인)
         */
        Favorite favorite = favoriteRepository.findByFavoriterIdAndTabId(favoriteId, tabId)
                .filter(f -> !f.isDeleted())
                .orElseThrow(() -> new RuntimeException("즐겨찾기가 존재하지 않습니다."));


        /**
         * 소프트 삭제
         */
        favorite.softDelete();
    }

    @Transactional
    public Boolean getFavorite(Long favoriterId, Long tabId) {
        boolean isFavorite = favoriteRepository.findByFavoriterIdAndTabId(favoriterId, tabId)
                .filter(favorite -> !favorite.isDeleted()) // 삭제 여부 필터링
                .isPresent(); // Optional 결과가 존재하면 true, 없으면 false

        return isFavorite;
    }
}
