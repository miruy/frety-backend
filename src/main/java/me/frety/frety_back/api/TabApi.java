package me.frety.frety_back.api;

import lombok.RequiredArgsConstructor;
import me.frety.frety_back.api.spec.TabSpec;
import me.frety.frety_back.domain.tab.request.CreateTabRequest;
import me.frety.frety_back.domain.tab.request.UpdateTabRequest;
import me.frety.frety_back.domain.tab.response.GetTabByIdResponse;
import me.frety.frety_back.domain.tab.response.SearchTabsResponse;
import me.frety.frety_back.domain.tab.usecase.TabUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tabs")
@RequiredArgsConstructor
public class TabApi implements TabSpec {
    private final TabUseCase tabUseCase;

    @PostMapping
    public ResponseEntity<Long> createTab(@RequestBody CreateTabRequest request) {
        Long tabId = tabUseCase.createTab(request);

        return ResponseEntity.created(URI.create(tabId.toString())).body(tabId);
    }

    @PatchMapping("/{tabId}")
    public ResponseEntity<Void> updateTab(@PathVariable Long tabId, @RequestBody UpdateTabRequest request) {
        tabUseCase.updateTab(tabId, request);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{tabId}")
    public ResponseEntity<Void> deleteTab(@PathVariable Long tabId) {
        tabUseCase.deleteTab(tabId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<SearchTabsResponse>> searchTabs() {
        List<SearchTabsResponse> body = tabUseCase.searchTabs();

        return ResponseEntity.ok(body);
    }

    @GetMapping("/{tabId}")
    public ResponseEntity<GetTabByIdResponse> getTabById(@PathVariable Long tabId) {
        GetTabByIdResponse body = tabUseCase.getTabById(tabId);

        return ResponseEntity.ok(body);
    }
}
