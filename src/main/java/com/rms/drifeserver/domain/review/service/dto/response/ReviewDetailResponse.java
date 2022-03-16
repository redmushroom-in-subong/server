package com.rms.drifeserver.domain.review.service.dto.response;

import com.rms.drifeserver.domain.review.model.Review;
import com.rms.drifeserver.domain.review.model.ReviewKeywordType;
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

    //user 정보
    private Long userId;

    private String nickName;

    private String profileImage;

    private String badgeName;

    private String myStoreTier;

    private Long myReviewCount;

    private Long myVisitCount;
    //user 정보

    private final List<ReviewKeywordTypeResponse> reviewKeywordTypes = new ArrayList<>();

    private List<String> images;

    @Builder(access = AccessLevel.PRIVATE)
    public ReviewDetailResponse(Long reviewId, String contents, @Nullable Long storeId, @Nullable String storeName, Long storeVisitCount, Long storeReviewCount, Long storeCustomCount, Long userId, String nickName, String profileImage, String badgeName, String myStoreTier, Long myReviewCount, Long myVisitCount) {
        this.reviewId = reviewId;
        this.contents = contents;
        this.storeId = storeId;
        this.storeName = storeName;
        this.storeVisitCount = storeVisitCount;
        this.storeReviewCount = storeReviewCount;
        this.storeCustomCount = storeCustomCount;
        this.userId = userId;
        this.nickName = nickName;
        this.profileImage = profileImage;
        this.badgeName = badgeName;
        this.myStoreTier = myStoreTier;
        this.myReviewCount = myReviewCount;
        this.myVisitCount = myVisitCount;
    }

    public static ReviewDetailResponse of(Review review, ReviewCounterResponse counter) {
        ReviewDetailResponse response = ReviewDetailResponse.builder()
                .reviewId(review.getId())
                .contents(review.getContents())
                .storeId(1L)
                .storeName("")
                .storeVisitCount(counter.getStoreVisitCount())
                .storeReviewCount(counter.getStoreReviewCount())
                .storeCustomCount(counter.getStoreCustomCount())
                .userId(review.getUser().getId())
                .nickName(review.getUser().getUsername())
                .profileImage(review.getUser().getProfileImageUrl())
                .badgeName(review.getUser().getMyBadge().getBadgeCode().getBadgeName())
                .myStoreTier(counter.getMyStoreTier())
                .myReviewCount(counter.getMyReviewCount())
                .myVisitCount(counter.getMyVisitCount())
                .build();

        review.getReviewKeywords()
                .forEach(reviewKeyword -> response.reviewKeywordTypes.add(ReviewKeywordTypeResponse.of(reviewKeyword.getReviewKeywordType())));
        return response;
    }
}
