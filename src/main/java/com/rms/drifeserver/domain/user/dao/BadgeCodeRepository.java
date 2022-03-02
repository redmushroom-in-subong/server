package com.rms.drifeserver.domain.user.dao;

import com.rms.drifeserver.domain.user.model.BadgeCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeCodeRepository extends JpaRepository<BadgeCode,Long> {
}
