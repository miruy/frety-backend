package me.frety.frety_back.domain.comment.repository;

import me.frety.frety_back.domain.comment.entity.Comment;
import me.frety.frety_back.domain.comment.entity.CommentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTabIdAndTypeAndDeletedAtIsNull(Long tabId, CommentType type);
}
