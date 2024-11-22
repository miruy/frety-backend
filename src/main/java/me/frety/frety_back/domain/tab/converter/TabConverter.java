package me.frety.frety_back.domain.tab.converter;

import me.frety.frety_back.domain.account.entity.Account;
import me.frety.frety_back.domain.account.entity.Privacy;
import me.frety.frety_back.domain.common.response.PageRs;
import me.frety.frety_back.domain.tab.entity.Tab;
import me.frety.frety_back.domain.tab.response.GetTabByIdResponse;
import me.frety.frety_back.domain.tab.response.SearchTabsByKeywordResponse;
import me.frety.frety_back.domain.tab.response.SearchTabsResponse;
import me.frety.frety_back.domain.tab.response.SearchTabsResponse__Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TabConverter {
    public SearchTabsResponse toSearchTabsResponse(Tab tab) {

        Account author = tab.getAuthor();

        Privacy authorPrivacy = author.getPrivacy();

        SearchTabsResponse__Author searchTabsResponse__author = SearchTabsResponse__Author.builder()
                .id(author.getId())
                .name(authorPrivacy.getName())
                .build();

        return SearchTabsResponse.builder()
                .id(tab.getId())
                .artist(tab.getArtist())
                .song(tab.getSong())
                .createdAt(tab.getCreatedAt())
                .updatedAt(tab.getUpdatedAt())
                .author(searchTabsResponse__author)
                .voteCount(tab.getVotes().size())
                .build();
    }

    public List<SearchTabsResponse> toSearchTabsResponseList(List<Tab> tabs) {
        return tabs.stream()
                .map(this::toSearchTabsResponse)
                .toList();
    }

    public PageRs<SearchTabsResponse> toSearchTabsResponsePage(Page<Tab> page) {
        PageImpl<SearchTabsResponse> dtoPage = new PageImpl<>(
                this.toSearchTabsResponseList(page.getContent()), page.getPageable(), page.getTotalElements());

        return PageRs.fromPage(dtoPage);
    }

    public GetTabByIdResponse toGetTabByIdResponse(Tab tab) {

        Integer favoriteCount = (int) tab.getFavorites().stream()
                .filter(favorite -> favorite.getDeletedAt() == null)  // 삭제되지 않은 즐겨찾기만 카운트
                .count();

        return GetTabByIdResponse.builder()
                .id(tab.getId())
                .artist(tab.getArtist())
                .song(tab.getSong())
                .capo(tab.getCapo())
                .style(tab.getStyle())
                .content(tab.getContent())
                .authorName(tab.getAuthorName())
                .createdAt(tab.getCreatedAt())
                .updatedAt(tab.getUpdatedAt())
                .voteCount(tab.getVotes().size())
                .favoriteCount(favoriteCount)
                .build();
    }
}
