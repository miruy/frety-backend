package me.frety.frety_back.domain.comment.service;

import lombok.RequiredArgsConstructor;
import me.frety.frety_back.domain.comment.converter.CommentConverter;
import me.frety.frety_back.domain.comment.entity.Comment;
import me.frety.frety_back.domain.comment.repository.CommentRepository;
import me.frety.frety_back.domain.comment.request.CreateCommentRequest;
import me.frety.frety_back.domain.comment.request.SearchCommentsCondition;
import me.frety.frety_back.domain.comment.request.UpdateCommentRequest;
import me.frety.frety_back.domain.comment.response.GetCommentByIdResponse;
import me.frety.frety_back.domain.comment.response.SearchCommentsResponse;
import me.frety.frety_back.domain.comment.usecase.CommentUseCase;
import me.frety.frety_back.domain.tab.entity.Tab;
import me.frety.frety_back.domain.tab.repository.TabRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService implements CommentUseCase {
    private final CommentRepository commentRepository;
    private final TabRepository tabRepository;

    private final CommentConverter commentConverter;

    @Override
    @Transactional
    public Long createComment(CreateCommentRequest request) {
        /**
         * 댓글 생성
         */
        Comment comment = Comment.builder()
                .content(request.getContent())
                .userName(request.getUserName())
                .build();

        /**
         * 댓글과 targetId 연결
         */
        switch (request.getType()) {
            case TAB -> {
                Tab tab = tabRepository.findById(request.getTargetId())
                        .filter(t -> !t.isDeleted())
                        .orElseThrow(() -> new RuntimeException("악보가 존재하지 않습니다."));

                comment.connectTab(tab);
            }
            default -> throw new RuntimeException("지원되지 않는 타입입니다. 댓글 생성 실패");
        }

        if (request.getParentCommentId() != null) {
            Comment parentComment = commentRepository.findById(request.getParentCommentId())
                    .filter(t -> !t.isDeleted())
                    .orElseThrow(() -> new RuntimeException("not found parentComment"));
            if (parentComment.getParentComment() != null) {
                throw new RuntimeException("대댓글에 댓글을 달 수 없습니다.");
            }

            comment.connectParentComment(parentComment);
        }

        /**
         * 댓글 저장
         */
        commentRepository.save(comment);

        return comment.getId();
    }

    @Override
    @Transactional
    public void updateComment(Long commentId, UpdateCommentRequest request) {
        /**
         * 댓글 단건 조회
         */
        Comment comment = commentRepository.findById(commentId)
                .filter(t -> !t.isDeleted())
                .orElseThrow(() -> new RuntimeException("댓글이 존재하지 않습니다."));

        /**
         * 업데이트
         */
        Optional.ofNullable(request.getContent()).ifPresent(comment::changeContent);
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId) {
        /**
         * 댓글 단건 조회
         */
        Comment comment = commentRepository.findById(commentId)
                .filter(t -> !t.isDeleted())
                .orElseThrow(() -> new RuntimeException("댓글이 존재하지 않습니다."));

        /**
         * 소프트 삭제
         */
        comment.softDelete();
    }

    @Override
    public GetCommentByIdResponse getCommentById(Long commentId) {
        /**
         * 댓글 단건 조회
         */
        Comment comment = commentRepository.findById(commentId)
                .filter(t -> !t.isDeleted())
                .orElseThrow(() -> new RuntimeException("댓글이 존재하지 않습니다."));

        return commentConverter.toGetCommentByIdResponse(comment);
    }

    @Override
    public List<SearchCommentsResponse> searchComments(SearchCommentsCondition condition) {
        List<Comment> comments = switch (condition.getType()) {
            case TAB ->
                    commentRepository.findByTabIdAndTypeAndDeletedAtIsNull(condition.getTargetId(), condition.getType());
            default -> throw new RuntimeException("댓글이 존재하지 않습니다.");
        };

        return commentConverter.toSearchTabsResponseList(comments);
    }

}
