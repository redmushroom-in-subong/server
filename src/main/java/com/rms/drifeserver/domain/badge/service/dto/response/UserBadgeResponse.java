package com.rms.drifeserver.domain.badge.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserBadgeResponse {
    private Long badgeId;
    private String badgeName;
    private boolean isActive;
}
