package me.frety.frety_back.domain.comment.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import me.frety.frety_back.domain.account.entity.Account;
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
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Comment extends BaseEntity {
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tab tab;

    @Enumerated(STRING)
    private CommentType type;

    @Column(name = "userName", nullable = false)
    private String userName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account author;

    @Builder.Default
    @OneToMany(mappedBy = "parentComment")
    private List<Comment> childComments = new ArrayList<>();

    public void changeContent(String content) {
        this.content = content;
    }

    public void changeUserName(String userName) {
        this.userName = userName;
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
