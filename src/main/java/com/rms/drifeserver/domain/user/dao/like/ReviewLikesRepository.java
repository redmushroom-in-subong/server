package com.rms.drifeserver.domain.user.dao.like;

import com.rms.drifeserver.domain.user.model.like.ReviewLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewLikesRepository extends JpaRepository<ReviewLikes,Long> {
}
