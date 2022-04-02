package com.rms.drifeserver.domain.review.service.dto.response;

import com.rms.drifeserver.domain.review.model.Review;
import com.rms.drifeserver.domain.review.model.ReviewKeywordType;
import com.rms.drifeserver.domain.user.service.dto.response.UserInfo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewDetailResponse {

    private Long reviewId;

    private String contents;

    //store 정보
    private Long storeId;

    private String storeName;

    private Long storeVisitCount;

    private Long storeReviewCount;

    private Long storeCustomCount;
    //store 정보

    private Long reviewLikes;

    //user 정보
    private UserInfo userInfo;

    private String badgeName;

    private String myStoreTier;

    private Long myReviewCount;

    private Long myVisitCount;

    private Boolean myIsLiked;
    //user 정보

    private final List<ReviewKeywordTypeResponse> reviewKeywordTypes = new ArrayList<>();

    private List<String> reviewImages;

    @Builder(access = AccessLevel.PRIVATE)
    private ReviewDetailResponse(Long reviewId, String contents, List<String> reviewImages, Long storeId,
                                String storeName, Long storeVisitCount, Long storeReviewCount, Long storeCustomCount,
                                Long reviewLikes, UserInfo userInfo, @Nullable String badgeName, String myStoreTier,
                                Long myReviewCount, Boolean myIsLiked, Long myVisitCount) {
        this.reviewId = reviewId;
        this.contents = contents;
        this.reviewImages = reviewImages;
        this.storeId = storeId;
        this.storeName = storeName;
        this.storeVisitCount = storeVisitCount;
        this.storeReviewCount = storeReviewCount;
        this.storeCustomCount = storeCustomCount;
        this.reviewLikes = reviewLikes;
        this.userInfo = userInfo;
        this.badgeName = badgeName;
        this.myStoreTier = myStoreTier;
        this.myReviewCount = myReviewCount;
        this.myVisitCount = myVisitCount;
        this.myIsLiked = myIsLiked;
    }

    public static ReviewDetailResponse of(Review review, ReviewCounterResponse counter) {
        ReviewDetailResponse response = ReviewDetailResponse.builder()
                .reviewId(review.getId())
                .contents(review.getContents())
                .reviewImages(review.getReviewImages().stream()
                        .map(reviewImage -> reviewImage.getImageUrl())
                        .collect(Collectors.toList()))
                .storeId(review.getStore().getId())
                .storeName(review.getStore().getStoreName())
                .storeVisitCount(counter.getStoreReviewCountInfo().getStoreVisitCount())
                .storeReviewCount(counter.getStoreReviewCountInfo().getStoreReviewCount())
                .storeCustomCount(counter.getStoreReviewCountInfo().getStoreCustomCount())
                .reviewLikes(counter.getReviewLikes())
                .userInfo(UserInfo.of(review.getUser()))
                .badgeName(review.getUser().getMyBadge().getBadgeCode().getBadgeName())
                .myStoreTier(counter.getMyStoreTier())
                .myReviewCount(counter.getMyReviewCount())
                .myVisitCount(counter.getMyVisitCount())
                .myIsLiked(counter.getMyIsLiked())
                .build();

        review.getReviewKeywords()
                .forEach(reviewKeyword -> response.reviewKeywordTypes.add(ReviewKeywordTypeResponse.of(reviewKeyword.getReviewKeywordType())));
        return response;
    }
}
