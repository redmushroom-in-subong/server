package com.rms.drifeserver.domain.like.dao;

import com.rms.drifeserver.domain.like.model.ReviewLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewLikesRepository extends JpaRepository<ReviewLikes,Long> {
}
