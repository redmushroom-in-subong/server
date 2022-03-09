package com.rms.drifeserver.domain.like.dao;

import com.rms.drifeserver.domain.like.model.CommentLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikesRepository extends JpaRepository<CommentLikes,Long> {
}
