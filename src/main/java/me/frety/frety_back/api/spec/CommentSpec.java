package me.frety.frety_back.api.spec;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.frety.frety_back.domain.comment.request.CreateCommentRequest;
import me.frety.frety_back.domain.comment.request.UpdateCommentRequest;
import me.frety.frety_back.domain.comment.response.GetCommentByIdResponse;
import org.springframework.http.ResponseEntity;

@Tag(name = "Comment", description = "댓글")
public interface CommentSpec {
    @Operation(summary = "댓글 생성")
    ResponseEntity<Long> createComment(CreateCommentRequest request);

    @Operation(summary = "댓글 수정")
    ResponseEntity<Void> updateComment(Long commentId, UpdateCommentRequest request);

    @Operation(summary = "댓글 삭제")
    ResponseEntity<Void> deleteComment(Long commentId);

    @Operation(summary = "댓글 조회")
    ResponseEntity<GetCommentByIdResponse> getCommentById(Long tabCommentId);
}
