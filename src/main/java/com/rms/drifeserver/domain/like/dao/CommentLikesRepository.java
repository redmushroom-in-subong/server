package com.rms.drifeserver.domain.like.dao;

import com.rms.drifeserver.domain.comment.model.Comment;
import com.rms.drifeserver.domain.like.model.CommentLikes;
import com.rms.drifeserver.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikesRepository extends JpaRepository<CommentLikes, Long> {

    Long countByComment(Comment comment);

    CommentLikes findByUserAndComment(User user, Comment comment);

}
