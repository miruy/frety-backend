package me.frety.frety_back.domain.comment.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.frety.frety_back.domain.comment.entity.CommentType;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentRequest {
    @NotNull(message = "targetId is required")
    private Long targetId;

    @NotNull(message = "type is required")
    private CommentType type;

    @NotNull(message = "content is required")
    private String content;

    private Long parentCommentId;

    @NotNull(message = "userName is required")
    private String userName;
}
