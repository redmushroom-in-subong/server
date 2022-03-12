package com.rms.drifeserver.domain.review.dao;

import com.rms.drifeserver.domain.review.model.Review;
import com.rms.drifeserver.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByIdAndUserId(Long id, Long userId);
}
