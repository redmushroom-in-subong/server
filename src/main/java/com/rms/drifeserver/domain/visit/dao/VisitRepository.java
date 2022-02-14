package com.rms.drifeserver.domain.visit.dao;

import com.rms.drifeserver.domain.visit.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, Long> {
}
