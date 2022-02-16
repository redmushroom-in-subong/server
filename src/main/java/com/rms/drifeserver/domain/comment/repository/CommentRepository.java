package com.rms.drifeserver.domain.comment.repository;

import com.rms.drifeserver.domain.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
