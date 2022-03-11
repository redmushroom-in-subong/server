package com.rms.drifeserver.domain.user.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class EditProfileReq {
    private String profileImageUrl;
    private String nickname;
}
