package me.frety.frety_back.domain.tab.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTabRequest {
    private String artist;
    private String song;
    private String capo;
    private String style;
    private String content;
    private String authorName;
}
