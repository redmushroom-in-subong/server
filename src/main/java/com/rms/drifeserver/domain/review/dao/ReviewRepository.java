package com.rms.drifeserver.domain.review.dao;

import com.rms.drifeserver.domain.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
