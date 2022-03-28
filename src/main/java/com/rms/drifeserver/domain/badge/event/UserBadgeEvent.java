package com.rms.drifeserver.domain.badge.event;

import com.rms.drifeserver.domain.badge.service.BadgeService;
import lombok.*;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;

@Getter
public class UserBadgeEvent {
    private Long userId;
    public UserBadgeEvent(Object source,Long userId){
        this.userId=userId;
    }
}
