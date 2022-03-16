package com.rms.drifeserver.domain.review.service.dto.response;

import com.rms.drifeserver.domain.review.service.ReviewServiceUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewCounterResponse {
    private long storeVisitCount;
    private long storeReviewCount;
    private long storeCustomCount;
    private long myVisitCount;
    private long myReviewCount;
    private String myStoreTier;

    static public ReviewCounterResponse of(long storeVisitCount, long storeReviewCount, long storeCustomCount
            , long myVisitCount, long myReviewCount, String myStoreTier) {
        return new ReviewCounterResponse(storeVisitCount, storeReviewCount, storeCustomCount, myVisitCount,
                myReviewCount, myStoreTier);
    }
}
