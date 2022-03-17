package com.rms.drifeserver.domain.badge.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserBadgeResponse {
    private Long badgeId;
    private String badgeName;
    private boolean isActive;
    public static UserBadgeResponse of(Map<String,Object> mp){
        return new UserBadgeResponse((Long)mp.get("badgeId"),
                (String)mp.get("badgeName"),
                (boolean) mp.get("isActive"));
    }
}
