package me.frety.frety_back.domain.tab.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.frety.frety_back.domain.vote.entity.Vote;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetTabByIdResponse {
    private Long id;
    private String artist;
    private String song;
    private String capo;
    private String style;
    private String content;
    private String authorName;
    private Integer voteCount;
    private Integer favoriteCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
