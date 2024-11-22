package me.frety.frety_back.api.spec;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.frety.frety_back.domain.comment.request.CreateCommentRequest;
import me.frety.frety_back.domain.comment.request.SearchCommentsCondition;
import me.frety.frety_back.domain.comment.request.UpdateCommentRequest;
import me.frety.frety_back.domain.comment.response.GetCommentByIdResponse;
import me.frety.frety_back.domain.comment.response.SearchCommentsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "Comment", description = "댓글")
public interface CommentSpec {
    @Operation(summary = "댓글 생성")
    ResponseEntity<Long> createComment(CreateCommentRequest request);

    @Operation(summary = "댓글 수정")
    ResponseEntity<Void> updateComment(@Parameter(name = "commentId") @PathVariable("commentId") Long commentId, UpdateCommentRequest request);

    @Operation(summary = "댓글 삭제")
    ResponseEntity<Void> deleteComment(@Parameter(name = "commentId") @PathVariable("commentId") Long commentId);

    @Operation(summary = "댓글 조회")
    ResponseEntity<GetCommentByIdResponse> getCommentById(@Parameter(name = "commentId") @PathVariable("commentId") Long tabCommentId);

    @Operation(summary = "댓글 전체 조회")
    ResponseEntity<List<SearchCommentsResponse>> searchComments(SearchCommentsCondition condition);
}
