package com.rms.drifeserver.domain.badge.service.impl;

import com.rms.drifeserver.domain.badge.dao.BadgeRepository;
import com.rms.drifeserver.domain.badge.model.Badge;
import com.rms.drifeserver.domain.badge.model.BadgeCode;
import com.rms.drifeserver.domain.user.dao.UserRepository;
import com.rms.drifeserver.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BadgeServiceImpl {
    final private BadgeRepository badgeRepository;
    void addBadge(Long userId,Long badgeId){
        Badge badge=new Badge(0l,new BadgeCode(),new User());
        badge.getUser().setId(userId);
        badge.getBadgeCode().setId(badgeId);
        badgeRepository.save(badge);
    }
}
