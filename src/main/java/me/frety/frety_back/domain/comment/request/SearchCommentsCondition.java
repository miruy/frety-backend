package me.frety.frety_back.domain.comment.request;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import me.frety.frety_back.domain.comment.entity.CommentType;
import org.springdoc.core.annotations.ParameterObject;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ParameterObject
public class SearchCommentsCondition {
    @Parameter(description = "타입과 관련된 ID")
    @NotNull(message = "targetId is required")
    private Long targetId;

    @Parameter(description = "댓글 타입")
    @NotNull(message = "type is required")
    private CommentType type;
}
