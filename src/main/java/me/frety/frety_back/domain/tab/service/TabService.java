package me.frety.frety_back.domain.tab.service;

import jdk.jfr.Frequency;
import lombok.RequiredArgsConstructor;
import me.frety.frety_back.domain.tab.converter.TabConverter;
import me.frety.frety_back.domain.tab.entity.Tab;
import me.frety.frety_back.domain.tab.repository.TabRepository;
import me.frety.frety_back.domain.tab.request.CreateTabRequest;
import me.frety.frety_back.domain.tab.request.UpdateTabRequest;
import me.frety.frety_back.domain.tab.response.GetTabByIdResponse;
import me.frety.frety_back.domain.tab.response.SearchTabsResponse;
import me.frety.frety_back.domain.tab.usecase.TabUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TabService implements TabUseCase {
    private final TabRepository tabRepository;

    private final TabConverter tabConverter;

    @Override
    @Transactional
    public Long createTab(CreateTabRequest request) {
        /**
         * 악보 생성
         */
        Tab tab = Tab.builder()
                .artist(request.getArtist())
                .song(request.getSong())
                .capo(request.getCapo())
                .style(request.getStyle())
                .content(request.getContent())
                .build();

        /**
         * 악보 저장
         */
        tabRepository.save(tab);

        return tab.getId();
    }

    @Override
    @Transactional
    public void updateTab(Long tabId, UpdateTabRequest request) {
        /**
         * 악보 조회
         */
        Tab tab = tabRepository.findById(tabId)
                .filter(t -> !t.isDeleted())
                .orElseThrow(() -> new RuntimeException("악보가 존재하지 않습니다."));

        /**
         * 업데이트
         */
        Optional.ofNullable(request.getArtist()).ifPresent(tab::changeArtist);
        Optional.ofNullable(request.getSong()).ifPresent(tab::changeSong);
        Optional.ofNullable(request.getCapo()).ifPresent(tab::changeCapo);
        Optional.ofNullable(request.getStyle()).ifPresent(tab::changeStyle);
        Optional.ofNullable(request.getContent()).ifPresent(tab::changeContent);
    }

    @Override
    @Transactional
    public void deleteTab(Long tabId) {
        /**
         * 악보 조회
         */
        Tab tab = tabRepository.findById(tabId)
                .filter(t -> !t.isDeleted())
                .orElseThrow(() -> new RuntimeException("악보가 존재하지 않습니다."));

        /**
         * 소프트 삭제
         */
        tab.softDelete();
    }

    @Override
    public List<SearchTabsResponse> searchTabs() {
        List<Tab> tabs = tabRepository.findAllByDeletedAtIsNull();

        return tabConverter.toSearchTabsResponseList(tabs);
    }

    @Override
    public GetTabByIdResponse getTabById(Long tabId) {
        /**
         * 악보 조회
         */
        Tab tab = tabRepository.findById(tabId)
                .filter(t -> !t.isDeleted())
                .orElseThrow(() -> new RuntimeException("악보가 존재하지 않습니다."));

        return tabConverter.toGetTabByIdResponse(tab);
    }
}
