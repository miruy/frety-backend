package me.frety.frety_back.api.spec;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.frety.frety_back.domain.tab.request.CreateTabRequest;
import me.frety.frety_back.domain.tab.request.UpdateTabRequest;
import me.frety.frety_back.domain.tab.response.GetTabByIdResponse;
import me.frety.frety_back.domain.tab.response.SearchTabsResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Tab", description = "악보")
public interface TabSpec {
    @Operation(summary = "악보 생성")
    ResponseEntity<Long> createTab(CreateTabRequest request);

    @Operation(summary = "악보 수정")
    ResponseEntity<Void> updateTab(Long tabId, UpdateTabRequest request);

    @Operation(summary = "악보 삭제")
    ResponseEntity<Void> deleteTab(Long tabId);

    @Operation(summary = "악보 검색")
    ResponseEntity<List<SearchTabsResponse>> searchTabs();

    @Operation(summary = "악보 단건 조회")
    ResponseEntity<GetTabByIdResponse> getTabById(Long tabId);
}
