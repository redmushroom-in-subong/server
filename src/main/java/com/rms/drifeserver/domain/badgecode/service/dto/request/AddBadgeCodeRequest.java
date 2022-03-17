package com.rms.drifeserver.domain.badgecode.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddBadgeCodeRequest {
    private String badgeName;
    private int reviewCount;
}
