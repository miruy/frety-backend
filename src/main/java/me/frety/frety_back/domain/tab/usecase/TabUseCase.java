package me.frety.frety_back.domain.tab.usecase;

import jakarta.validation.Valid;
import me.frety.frety_back.domain.common.request.PageRq;
import me.frety.frety_back.domain.common.response.PageRs;
import me.frety.frety_back.domain.tab.request.CreateTabRequest;
import me.frety.frety_back.domain.tab.request.SearchTabsCondition;
import me.frety.frety_back.domain.tab.request.UpdateTabRequest;
import me.frety.frety_back.domain.tab.response.GetTabByIdResponse;
import me.frety.frety_back.domain.tab.response.SearchTabsResponse;
import org.springframework.validation.annotation.Validated;


@Validated
public interface TabUseCase {
    Long createTab(@Valid CreateTabRequest request, Long authenticatedAccountId);

    void updateTab(Long tabId, @Valid UpdateTabRequest request, Long authenticatedAccountId);

    void deleteTab(Long tabId, Long authenticatedAccountId);

    PageRs<SearchTabsResponse> searchTabs(PageRq pageRq, SearchTabsCondition condition);

    PageRs<SearchTabsResponse> searchMyCreatedTabs(PageRq pageRq, String authorName);

    GetTabByIdResponse getTabById(Long tabId);
}
