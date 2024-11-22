package me.frety.frety_back.domain.comment.converter;

import me.frety.frety_back.domain.comment.entity.Comment;
import me.frety.frety_back.domain.comment.response.GetCommentByIdResponse;
import me.frety.frety_back.domain.comment.response.SearchCommentsResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentConverter {
    public GetCommentByIdResponse toGetCommentByIdResponse(Comment comment) {

        Long parentCommentId = (comment.getParentComment() != null) ? comment.getParentComment().getId() : null;

        return GetCommentByIdResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .userName(comment.getUserName())
                .parentCommentId(parentCommentId)
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }

    public SearchCommentsResponse toSearchCommentsResponse(Comment comment) {
        return SearchCommentsResponse.builder()
                .id(comment.getId())
                .build();
    }

    public List<SearchCommentsResponse> toSearchTabsResponseList(List<Comment> comments) {
        return comments.stream()
                .map(this::toSearchCommentsResponse)
                .toList();
    }
}
