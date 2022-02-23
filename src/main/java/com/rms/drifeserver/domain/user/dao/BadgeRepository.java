package com.rms.drifeserver.domain.user.dao;

import com.rms.drifeserver.domain.user.model.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeRepository extends JpaRepository<Badge,Long> {
}
