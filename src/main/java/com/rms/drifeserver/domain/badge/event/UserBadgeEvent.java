package com.rms.drifeserver.domain.badge.event;

import com.rms.drifeserver.domain.badge.service.BadgeService;
import lombok.*;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserBadgeEvent extends ApplicationEvent {
    private Long userId;
    public UserBadgeEvent(Object source){
        super(source);
    }
}
