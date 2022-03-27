package com.rms.drifeserver.domain.review.service.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StoreReviewCountInfoResponse {
    private Long storeVisitCount;
    private Long storeReviewCount;
    private Long storeCustomCount;

    static public StoreReviewCountInfoResponse of(Long storeVisitCount, Long storeReviewCount, Long storeCustomCount) {
        return new StoreReviewCountInfoResponse(storeVisitCount, storeReviewCount, storeCustomCount);
    }
}
