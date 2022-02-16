package com.rms.drifeserver.domain.review.dao;

import com.rms.drifeserver.domain.review.model.ReviewKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewKeywordRepository extends JpaRepository<ReviewKeyword, Long> {
}
