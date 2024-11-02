package me.frety.frety_back.domain.tab.converter;

import me.frety.frety_back.domain.tab.entity.Tab;
import me.frety.frety_back.domain.tab.response.GetTabByIdResponse;
import me.frety.frety_back.domain.tab.response.SearchTabsResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TabConverter {
    public SearchTabsResponse toSearchTabsResponse(Tab tab) {
        return SearchTabsResponse.builder()
                .id(tab.getId())
                .artist(tab.getArtist())
                .song(tab.getSong())
                .createdAt(tab.getCreatedAt())
                .updatedAt(tab.getUpdatedAt())
                .build();
    }

    public List<SearchTabsResponse> toSearchTabsResponseList(List<Tab> tabs) {
        return tabs.stream()
                .map(this::toSearchTabsResponse)
                .toList();
    }

    public GetTabByIdResponse toGetTabByIdResponse(Tab tab) {
        return GetTabByIdResponse.builder()
                .id(tab.getId())
                .artist(tab.getArtist())
                .song(tab.getSong())
                .capo(tab.getCapo())
                .style(tab.getStyle())
                .content(tab.getContent())
                .createdAt(tab.getCreatedAt())
                .updatedAt(tab.getUpdatedAt())
                .build();
    }
}
