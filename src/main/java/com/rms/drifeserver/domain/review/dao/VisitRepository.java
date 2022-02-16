package com.rms.drifeserver.domain.review.dao;

import com.rms.drifeserver.domain.review.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, Long> {
}
