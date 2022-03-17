package com.rms.drifeserver.domain.badge.service;

import com.rms.drifeserver.domain.badge.service.dto.response.UserBadgeResponse;
import com.rms.drifeserver.domain.common.exception.BaseException;

import java.util.List;

public interface BadgeService {
    void addBadge(Long userId,Long badgeId);
    public void checkBadgeEarnCondition(Long id) throws BaseException;
    List<UserBadgeResponse> findAllUserBadges();

}
