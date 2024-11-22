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
public class SearchTabsByKeywordResponse {
    private Long id;
    private String artist;
    private String song;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}


