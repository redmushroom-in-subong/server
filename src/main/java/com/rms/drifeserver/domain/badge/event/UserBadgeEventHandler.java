package com.rms.drifeserver.domain.badge.event;

import com.rms.drifeserver.domain.badge.service.BadgeService;
import com.rms.drifeserver.domain.common.exception.BaseException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class UserBadgeEventHandler {
    private final BadgeService badgeService;

    @TransactionalEventListener
    @Async
    public void onApplicationEvent(UserBadgeEvent event) throws Exception, BaseException {
        badgeService.checkBadgeEarnCondition(event.getUserId());
    }
}
