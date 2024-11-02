package me.frety.frety_back.domain.comment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import me.frety.frety_back.domain.common.entity.BaseEntity;
import me.frety.frety_back.domain.tab.entity.Tab;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;

@Getter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity {
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tab tab;

    @Enumerated(STRING)
    private CommentType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment")
    private List<Comment> childComments = new ArrayList<>();

    public void changeContent(String content) {
        this.content = content;
    }

    public void softDelete() {
        super.softDelete();
    }

    public void connectTab(Tab tab) {
        this.type = CommentType.TAB;
        this.tab = tab;
    }

    public void connectParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }
}
