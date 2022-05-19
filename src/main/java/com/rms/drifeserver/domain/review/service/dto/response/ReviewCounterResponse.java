package com.rms.drifeserver.domain.review.service.dto.response;

import com.rms.drifeserver.domain.review.service.ReviewServiceUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewCounterResponse {

    private StoreReviewCountInfoResponse storeReviewCountInfo;
    private Long reviewLikes;
    private Long myVisitCount;
    private Long myReviewCount;
    private Boolean myIsLiked;
    private String myStoreTier;

    public static ReviewCounterResponse of(StoreReviewCountInfoResponse storeReviewCountInfo,
            Long reviewLikes, Long myVisitCount, Long myReviewCount, Boolean myIsLiked) {
        return new ReviewCounterResponse(storeReviewCountInfo, reviewLikes, myVisitCount,
                myReviewCount, myIsLiked, getMyStoreTierByCount(myVisitCount));
    }

    private static String getMyStoreTierByCount(Long count) {

        if (count >= 20) {
            return "소울메이트";
        } else if (count >= 10) {
            return "절친한 사이";
        } else if (count >= 5) {
            return "친한 사이";
        } else if (count >= 1) {
            return "어색한 사이";
        }
        return "모르는 사이";
    }
}
