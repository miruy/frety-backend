package me.frety.frety_back.domain.tab.usecase;

import jakarta.validation.Valid;
import me.frety.frety_back.domain.tab.request.CreateTabRequest;
import me.frety.frety_back.domain.tab.request.UpdateTabRequest;
import me.frety.frety_back.domain.tab.response.GetTabByIdResponse;
import me.frety.frety_back.domain.tab.response.SearchTabsResponse;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface TabUseCase {
    Long createTab(@Valid CreateTabRequest request);
    void updateTab(Long tabId, @Valid UpdateTabRequest request);
    void deleteTab(Long tabId);
    List<SearchTabsResponse> searchTabs();
    GetTabByIdResponse getTabById(Long tabId);
}
