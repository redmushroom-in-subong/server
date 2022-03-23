package com.rms.drifeserver.domain.badgecode.service;

import com.rms.drifeserver.domain.badgecode.model.BadgeCode;
import com.rms.drifeserver.domain.badgecode.service.dto.request.AddBadgeCodeRequest;

import java.util.List;
import java.util.Optional;

public interface BadgeCodeService {
    Optional<BadgeCode> findBadgeCodeById(Long id);
    void addBadgeCode(AddBadgeCodeRequest addBadgeCodeRequest);
    void removeBadgeCode(Long id);
    List<BadgeCode> findAll();
}