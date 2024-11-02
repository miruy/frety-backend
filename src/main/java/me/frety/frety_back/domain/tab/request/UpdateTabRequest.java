package me.frety.frety_back.domain.tab.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTabRequest {
    private String artist;
    private String song;
    private String capo;
    private String style;
    private String content;
}
