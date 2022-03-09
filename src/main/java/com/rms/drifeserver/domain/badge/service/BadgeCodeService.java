package com.rms.drifeserver.domain.badge.service;

import com.rms.drifeserver.domain.badge.model.BadgeCode;
import com.rms.drifeserver.domain.badge.service.dto.response.UserBadgeResponse;

import java.util.List;
import java.util.Optional;

public interface BadgeCodeService {
    Optional<BadgeCode> findBadgeCodeById(Long id);
    void addBadgeCode(BadgeCode badgeCode);
    void removeBadgeCode(Long id);
    List<BadgeCode> findAll();
}