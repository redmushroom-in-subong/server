package com.rms.drifeserver.domain.user.dao.like;

import com.rms.drifeserver.domain.user.model.like.CommentLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikesRepository extends JpaRepository<CommentLikes,Long> {
}