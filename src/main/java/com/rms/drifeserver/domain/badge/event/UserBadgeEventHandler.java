package com.rms.drifeserver.domain.badge.event;

import com.rms.drifeserver.domain.badge.service.BadgeService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserBadgeEventHandler implements ApplicationListener<UserBadgeEvent> {
    private final BadgeService badgeService;

    @Override
    public void onApplicationEvent(UserBadgeEvent event) {
        System.out.println("check user badge event at "+event.getTimestamp());
        badgeService.checkBadgeEarnCondition(event.getUserId());
    }

}
