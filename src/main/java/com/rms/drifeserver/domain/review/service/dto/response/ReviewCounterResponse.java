package com.rms.drifeserver.domain.review.service.dto.response;

import com.rms.drifeserver.domain.review.service.ReviewServiceUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewCounterResponse {
    private Long storeVisitCount;
    private Long storeReviewCount;
    private Long storeCustomCount;
    private Long storeLikes;
    private Long myVisitCount;
    private Long myReviewCount;
    private Boolean myIsLiked;
    private String myStoreTier;

    static public ReviewCounterResponse of(Long storeVisitCount, Long storeReviewCount, Long storeCustomCount,
            Long storeLikes, Long myVisitCount, Long myReviewCount, Boolean myIsLiked, String myStoreTier) {
        return new ReviewCounterResponse(storeVisitCount, storeReviewCount, storeCustomCount, storeLikes, myVisitCount,
                myReviewCount, myIsLiked, myStoreTier);
    }
}
