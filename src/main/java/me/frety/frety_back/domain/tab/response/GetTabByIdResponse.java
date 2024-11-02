package me.frety.frety_back.domain.tab.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
