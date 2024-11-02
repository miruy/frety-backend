package me.frety.frety_back.domain.tab.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTabRequest {
    @NotNull(message = "artist is required")
    private String artist;

    @NotNull(message = "song is required")
    private String song;

    @NotNull(message = "capo is required")
    private String capo;

    @NotNull(message = "style is required")
    private String style;

    @NotNull(message = "content is required")
    private String content;
}
