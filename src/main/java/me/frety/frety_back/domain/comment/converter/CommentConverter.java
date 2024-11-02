package me.frety.frety_back.domain.comment.converter;

import me.frety.frety_back.domain.comment.entity.Comment;
import me.frety.frety_back.domain.comment.response.GetCommentByIdResponse;
import org.springframework.stereotype.Service;

@Service
public class CommentConverter {
    public GetCommentByIdResponse toGetCommentByIdResponse(Comment comment) {
        return GetCommentByIdResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }
}
