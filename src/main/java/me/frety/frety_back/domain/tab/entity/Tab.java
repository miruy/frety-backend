package me.frety.frety_back.domain.tab.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import me.frety.frety_back.domain.common.entity.BaseEntity;

@Getter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Tab extends BaseEntity {
    @Column(name = "artist", nullable = false)
    private String artist;

    @Column(name = "song", nullable = false)
    private String song;

    @Column(name = "capo", nullable = false)
    private String capo;

    @Column(name = "style", nullable = false)
    private String style;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    public void changeArtist(String artist) {
        this.artist = artist;
    }

    public void changeSong(String song) {
        this.song = song;
    }

    public void changeCapo(String capo) {
        this.capo = capo;
    }

    public void changeStyle(String style) {
        this.style = style;
    }

    public void changeContent(String content) {
        this.content = content;
    }

    public void softDelete() {
        super.softDelete();
    }
}
