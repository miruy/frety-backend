package me.frety.frety_back.domain.comment.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.frety.frety_back.domain.comment.entity.CommentType;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCommentRequest {
    private String content;
}
