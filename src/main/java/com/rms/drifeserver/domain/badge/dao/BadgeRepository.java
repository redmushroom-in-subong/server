package com.rms.drifeserver.domain.badge.dao;

import com.rms.drifeserver.domain.badge.model.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeRepository extends JpaRepository<Badge,Long> {
}
