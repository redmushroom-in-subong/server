package com.rms.drifeserver.domain.badge.event;

import com.rms.drifeserver.domain.badge.service.BadgeService;
import com.rms.drifeserver.domain.common.exception.BaseException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserBadgeEventHandler {
    private final BadgeService badgeService;

    @EventListener
    public void onApplicationEvent(UserBadgeEvent event) throws Exception, BaseException {
        System.out.println("check user badge event  ");
        badgeService.checkBadgeEarnCondition(event.getUserId());
    }

}
