package me.frety.frety_back.domain.tab.service;

import lombok.RequiredArgsConstructor;
import me.frety.frety_back.domain.account.entity.Account;
import me.frety.frety_back.domain.account.repository.AccountRepository;
import me.frety.frety_back.domain.common.entity.BaseEntity;
import me.frety.frety_back.domain.common.request.PageRq;
import me.frety.frety_back.domain.common.response.PageRs;
import me.frety.frety_back.domain.tab.converter.TabConverter;
import me.frety.frety_back.domain.tab.entity.Tab;
import me.frety.frety_back.domain.tab.repository.TabRepository;
import me.frety.frety_back.domain.tab.request.CreateTabRequest;
import me.frety.frety_back.domain.tab.request.SearchTabsCondition;
import me.frety.frety_back.domain.tab.request.UpdateTabRequest;
import me.frety.frety_back.domain.tab.response.GetTabByIdResponse;
import me.frety.frety_back.domain.tab.response.SearchTabsResponse;
import me.frety.frety_back.domain.tab.usecase.TabUseCase;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TabService implements TabUseCase {
    private final TabRepository tabRepository;
    private final AccountRepository accountRepository;

    private final TabConverter tabConverter;

    @Override
    @Transactional
    public Long createTab(CreateTabRequest request, Long authenticatedAccountId) {

        Account authenticatedAccount = accountRepository.findById(authenticatedAccountId)
                .filter(BaseEntity::isNotDeleted)
                .filter(Account::isUser)
                .orElseThrow(() -> new RuntimeException("인증되지 않은 유저입니다."));

        System.out.println("request.getAuthorName()" + request.getAuthorName());
        /**
         * 악보 생성
         */
        Tab tab = Tab.builder()
                .artist(request.getArtist())
                .song(request.getSong())
                .capo(request.getCapo())
                .style(request.getStyle())
                .content(request.getContent())
                .author(authenticatedAccount)
                .authorName(request.getAuthorName())
                .build();

        /**
         * 악보 저장
         */
        tabRepository.save(tab);

        return tab.getId();
    }

    @Override
    @Transactional
    public void updateTab(Long tabId, UpdateTabRequest request, Long authenticatedAccountId) {

        Account authenticatedAccount = accountRepository.findById(authenticatedAccountId)
                .filter(BaseEntity::isNotDeleted)
                .filter(Account::isUser)
                .orElseThrow(() -> new RuntimeException("인증되지 않은 유저입니다."));

        /**
         * 악보 조회
         */
        Tab tab = tabRepository.findById(tabId)
                .filter(BaseEntity::isNotDeleted)
                .orElseThrow(() -> new RuntimeException("악보가 존재하지 않습니다."));

        /**
         * 자신의 악보인지 체크
         */
        if (!tab.getAuthor().equals(authenticatedAccount)) {
            throw new RuntimeException("악보를 찾을 수 없습니다.");
        }

        /**
         * 업데이트
         */
        Optional.ofNullable(request.getArtist()).ifPresent(tab::changeArtist);
        Optional.ofNullable(request.getSong()).ifPresent(tab::changeSong);
        Optional.ofNullable(request.getCapo()).ifPresent(tab::changeCapo);
        Optional.ofNullable(request.getStyle()).ifPresent(tab::changeStyle);
        Optional.ofNullable(request.getContent()).ifPresent(tab::changeContent);
        Optional.ofNullable(request.getAuthorName()).ifPresent(tab::changeAuthorName);
    }

    @Override
    @Transactional
    public void deleteTab(Long tabId, Long authenticatedAccountId) {

        Account authenticatedAccount = accountRepository.findById(authenticatedAccountId)
                .filter(BaseEntity::isNotDeleted)
                .filter(Account::isUser)
                .orElseThrow(() -> new RuntimeException("인증되지 않은 유저입니다."));

        /**
         * 악보 조회
         */
        Tab tab = tabRepository.findById(tabId)
                .filter(BaseEntity::isNotDeleted)
                .orElseThrow(() -> new RuntimeException("악보가 존재하지 않습니다."));

        /**
         * 자신의 악보인지 체크
         */
        if (!tab.getAuthor().equals(authenticatedAccount)) {
            throw new RuntimeException("악보를 찾을 수 없습니다.");
        }

        /**
         * 소프트 삭제
         */
        tab.softDelete();
    }

    @Override
    public PageRs<SearchTabsResponse> searchTabs(PageRq pageRq, SearchTabsCondition condition) {
        Page<Tab> page = tabRepository.search(pageRq.toPageable(), condition);

        return tabConverter.toSearchTabsResponsePage(page);
    }

    @Override
    public PageRs<SearchTabsResponse> searchMyCreatedTabs(PageRq pageRq, Long authorId) {
        Page<Tab> page = tabRepository.searchByAuthor(pageRq.toPageable(), authorId);

        return tabConverter.toSearchTabsResponsePage(page);
    }

    @Override
    @Transactional
    public GetTabByIdResponse getTabById(Long tabId) {
        /**
         * 악보 조회
         */
        Tab tab = tabRepository.findById(tabId)
                .filter(BaseEntity::isNotDeleted)
                .orElseThrow(() -> new RuntimeException("악보가 존재하지 않습니다."));

        return tabConverter.toGetTabByIdResponse(tab);
    }
}
