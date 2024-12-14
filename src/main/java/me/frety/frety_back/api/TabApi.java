package me.frety.frety_back.api;

import lombok.RequiredArgsConstructor;
import me.frety.frety_back.api.spec.TabSpec;
import me.frety.frety_back.domain.common.request.PageRq;
import me.frety.frety_back.domain.common.response.PageRs;
import me.frety.frety_back.domain.tab.request.CreateTabRequest;
import me.frety.frety_back.domain.tab.request.SearchTabsCondition;
import me.frety.frety_back.domain.tab.request.UpdateTabRequest;
import me.frety.frety_back.domain.tab.response.GetTabByIdResponse;
import me.frety.frety_back.domain.tab.response.SearchTabsResponse;
import me.frety.frety_back.domain.tab.usecase.TabUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/tabs")
@RequiredArgsConstructor
public class TabApi implements TabSpec {
    private final TabUseCase tabUseCase;

    @PostMapping
    public ResponseEntity<Long> createTab(@RequestBody CreateTabRequest request, @AuthenticationPrincipal Jwt jwt) {
        Long tabId = tabUseCase.createTab(request, Long.valueOf(jwt.getSubject()));

        return ResponseEntity.created(URI.create(tabId.toString())).body(tabId);
    }

    @PatchMapping("/{tabId}")
    public ResponseEntity<Void> updateTab(
            @PathVariable("tabId") Long tabId,
            @RequestBody UpdateTabRequest request,
            @AuthenticationPrincipal Jwt jwt) {
        tabUseCase.updateTab(tabId, request, Long.valueOf(jwt.getSubject()));

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{tabId}")
    public ResponseEntity<Void> deleteTab(@PathVariable("tabId") Long tabId, @AuthenticationPrincipal Jwt jwt) {
        tabUseCase.deleteTab(tabId, Long.valueOf(jwt.getSubject()));

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<PageRs<SearchTabsResponse>> searchTabs(PageRq pageRq, SearchTabsCondition condition) {
        PageRs<SearchTabsResponse> body = tabUseCase.searchTabs(pageRq, condition);

        return ResponseEntity.ok(body);
    }

    @GetMapping("/{authorName}/created")
    public ResponseEntity<PageRs<SearchTabsResponse>> searchMyCreatedTabs(PageRq pageRq, @PathVariable("authorName") String authorName) {
        PageRs<SearchTabsResponse> body = tabUseCase.searchMyCreatedTabs(pageRq, authorName);

        return ResponseEntity.ok(body);
    }


    @GetMapping("/{tabId}")
    public ResponseEntity<GetTabByIdResponse> getTabById(@PathVariable("tabId") Long tabId) {
        GetTabByIdResponse body = tabUseCase.getTabById(tabId);

        return ResponseEntity.ok(body);
    }
}
