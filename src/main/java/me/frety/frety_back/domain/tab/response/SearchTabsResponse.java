package me.frety.frety_back.domain.tab.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchTabsResponse {
    private Long id;
    private String artist;
    private String song;
    private SearchTabsResponse__Author author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer voteCount;
}
