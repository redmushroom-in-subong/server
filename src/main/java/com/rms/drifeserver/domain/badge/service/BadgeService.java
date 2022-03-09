package com.rms.drifeserver.domain.badge.service;

import com.rms.drifeserver.domain.badge.service.dto.response.UserBadgeResponse;

import java.util.List;

public interface BadgeService {
    void addBadge(Long userId,Long badgeId);
    public void checkBadgeEarnCondition();
    List<UserBadgeResponse> findAllUserBadges(Long userId);

}
