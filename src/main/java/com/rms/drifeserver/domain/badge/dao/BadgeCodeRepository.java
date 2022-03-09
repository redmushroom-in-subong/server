package com.rms.drifeserver.domain.badge.dao;

import com.rms.drifeserver.domain.badge.model.BadgeCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BadgeCodeRepository extends JpaRepository<BadgeCode,Long> {

}
