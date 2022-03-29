package com.rms.drifeserver.domain.user.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EditRegionReq {
    private String regionName;
    private String regionCode;
}
