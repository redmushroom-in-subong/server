package com.rms.drifeserver.domain.common.util.geolocation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KakaoRegionResponse {
    private String regionCode;
    private String regionName;
}
