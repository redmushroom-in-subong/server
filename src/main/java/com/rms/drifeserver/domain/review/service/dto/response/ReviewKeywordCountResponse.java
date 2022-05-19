package com.rms.drifeserver.domain.review.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewKeywordCountResponse {

    private Long keywordId;
    private String keywordName;
    private Long cnt;
}
