package me.frety.frety_back.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import me.frety.frety_back.api.spec.CommentSpec;
import me.frety.frety_back.domain.comment.request.CreateCommentRequest;
import me.frety.frety_back.domain.comment.request.SearchCommentsCondition;
import me.frety.frety_back.domain.comment.request.UpdateCommentRequest;
import me.frety.frety_back.domain.comment.response.GetCommentByIdResponse;
import me.frety.frety_back.domain.comment.response.SearchCommentsResponse;
import me.frety.frety_back.domain.comment.usecase.CommentUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentApi implements CommentSpec {
    private final CommentUseCase commentUseCase;

    @PostMapping
    public ResponseEntity<Long> createComment(@RequestBody CreateCommentRequest request) {
        Long commentId = commentUseCase.createComment(request);

        return ResponseEntity.created(URI.create(commentId.toString())).body(commentId);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<Void> updateComment(@PathVariable("commentId") Long commentId, @RequestBody UpdateCommentRequest request) {
        commentUseCase.updateComment(commentId, request);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("commentId") Long commentId) {
        commentUseCase.deleteComment(commentId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<GetCommentByIdResponse> getCommentById(@PathVariable("commentId") Long commentId) {
        GetCommentByIdResponse body = commentUseCase.getCommentById(commentId);

        return ResponseEntity.ok(body);
    }

    @Operation // 페이지네이션 사용 안함
    @GetMapping
    public ResponseEntity<List<SearchCommentsResponse>> searchComments(SearchCommentsCondition condition) {
        List<SearchCommentsResponse> body = commentUseCase.searchComments(condition);

        return ResponseEntity.ok(body);
    }
}
