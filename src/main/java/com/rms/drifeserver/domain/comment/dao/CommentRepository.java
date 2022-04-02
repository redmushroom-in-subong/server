package com.rms.drifeserver.domain.comment.dao;

import com.rms.drifeserver.domain.board.model.Board;
import com.rms.drifeserver.domain.comment.model.Comment;
import com.rms.drifeserver.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByIdAndUser(Long id, User user);

    Long countByBoard(Board board);
}
