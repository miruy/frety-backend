package me.frety.frety_back.domain.comment.usecase;

import jakarta.validation.Valid;
import me.frety.frety_back.domain.comment.request.CreateCommentRequest;
import me.frety.frety_back.domain.comment.request.SearchCommentsCondition;
import me.frety.frety_back.domain.comment.request.UpdateCommentRequest;
import me.frety.frety_back.domain.comment.response.GetCommentByIdResponse;
import me.frety.frety_back.domain.comment.response.SearchCommentsResponse;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface CommentUseCase {
    Long createComment(@Valid CreateCommentRequest request);
    void updateComment(Long commentId, @Valid UpdateCommentRequest request);
    void deleteComment(Long commentId);
    GetCommentByIdResponse getCommentById(Long commentId);
    List<SearchCommentsResponse> searchComments(@Valid SearchCommentsCondition condition);
}
