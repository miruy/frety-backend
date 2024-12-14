package me.frety.frety_back.api.spec;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.frety.frety_back.domain.common.request.PageRq;
import me.frety.frety_back.domain.common.response.PageRs;
import me.frety.frety_back.domain.tab.request.CreateTabRequest;
import me.frety.frety_back.domain.tab.request.SearchTabsCondition;
import me.frety.frety_back.domain.tab.request.UpdateTabRequest;
import me.frety.frety_back.domain.tab.response.GetTabByIdResponse;
import me.frety.frety_back.domain.tab.response.SearchTabsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Tab", description = "악보")
public interface TabSpec {
    @Operation(summary = "악보 생성")
    @SecurityRequirement(name = "bearer-key")
    ResponseEntity<Long> createTab(CreateTabRequest request, Jwt jwt);

    @Operation(summary = "악보 수정")
    @SecurityRequirement(name = "bearer-key")
    ResponseEntity<Void> updateTab(@Parameter(name = "tabId") @PathVariable("tabId") Long tabId, UpdateTabRequest request, Jwt jwt);

    @Operation(summary = "악보 삭제")
    @SecurityRequirement(name = "bearer-key")
    ResponseEntity<Void> deleteTab(@Parameter(name = "tabId") @PathVariable("tabId") Long tabId, Jwt jwt);

    @Operation(summary = "악보 검색")
    ResponseEntity<PageRs<SearchTabsResponse>> searchTabs(PageRq pageRq, SearchTabsCondition condition);

    @Operation(summary = "내가 제작한 악보 검색")
    ResponseEntity<PageRs<SearchTabsResponse>> searchMyCreatedTabs(PageRq pageRq, String authorName);

    @Operation(summary = "악보 단건 조회")
    ResponseEntity<GetTabByIdResponse> getTabById(@Parameter(name = "tabId") @PathVariable("tabId") Long tabId);
}
