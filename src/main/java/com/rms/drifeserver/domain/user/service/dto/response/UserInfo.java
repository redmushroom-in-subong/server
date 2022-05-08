package com.rms.drifeserver.domain.user.service.dto.response;

import com.rms.drifeserver.domain.user.model.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserInfo {
    private Long userId;
    private String username;
    private String profileImageUrl;
    private String regionName;

    public static UserInfo of(User user) {
        return new UserInfo(user.getId(), user.getUsername(), user.getProfileImageUrl(),user.getRegionName());
    }
}
