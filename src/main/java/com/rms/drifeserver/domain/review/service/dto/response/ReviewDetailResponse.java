package com.rms.drifeserver.domain.review.service.dto.response;

import com.rms.drifeserver.domain.review.model.Review;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewDetailResponse {

    private Long reviewId;

    private String contents;

    //store 정보
    private Long storeId;

    private Long storeName;

    private Long storeVisitCount;

    private Long storeReviewCount;

    private Long storeCustomCount;
    //store 정보

    //user 정보
    private Long userId;

    private String nickName;

    private String profileImage;

    private String badgeName;

    private String myStoreTier;

    private Long myReviewCount;

    private Long myVisitCount;
    //user 정보


    private List<ReviewKeywordTypeResponse> reviewKeywordTypes;

    private List<String> images;

    private ReviewDetailResponse(Long reviewId, String contents) {
        this.reviewId = reviewId;
        this.contents = contents;
    }

    public static ReviewDetailResponse of(Review review) {
        return new ReviewDetailResponse(review.getId(), review.getContents());
    }
}
