package com.rms.drifeserver.domain.review.api;

import com.rms.drifeserver.domain.common.dto.ApiResponse;
import com.rms.drifeserver.domain.common.exception.type.ErrorCode;
import com.rms.drifeserver.domain.review.service.RetrieveReviewsService;
import com.rms.drifeserver.domain.review.service.ReviewService;
import com.rms.drifeserver.domain.review.service.dto.request.AddReviewRequest;
import com.rms.drifeserver.domain.review.service.dto.request.UpdateReviewRequest;
import com.rms.drifeserver.domain.review.service.dto.response.ReviewDetailResponse;
import com.rms.drifeserver.domain.review.service.dto.response.ReviewsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class ReviewApi {
    private final ReviewService reviewService;
    private final RetrieveReviewsService retrieveReviewsService;

    @GetMapping("/v1/stores/{storeId}/reviews")
    public ApiResponse<ReviewsResponse> getReviews(@RequestParam(required = false) Long userId, @RequestParam(required = false) Long keywordId, @PathVariable Long storeId) {
        if (userId == null && keywordId == null) {
           return ApiResponse.success(retrieveReviewsService.getAllReviewsInStore(storeId));
        }
        else if (userId == null) {
            return ApiResponse.success(retrieveReviewsService.getAllReviewsInStoreWithUserId(storeId, userId));
        }
        else if (keywordId == null) {
            return ApiResponse.success(retrieveReviewsService.getAllReviewsInStoreWithKeywordId(storeId, keywordId));
        }
        return ApiResponse.error(ErrorCode.INVALID);
    }

    @PostMapping("/v1/stores/{storeId}/reviews")
    public ApiResponse<Object> addReview(@RequestBody AddReviewRequest request, @PathVariable Long storeId) {
        //인증 필요
        reviewService.addReview(request, 1L, storeId);
        return ApiResponse.success(null);
    }

    @GetMapping("/v1/stores/{storeId}/reviews/{reviewId}")
    public ApiResponse<ReviewDetailResponse> getReview(@PathVariable Long storeId, @PathVariable Long reviewId) {
        return ApiResponse.success(reviewService.getReviewDetail(reviewId));
    }

    @PutMapping("/v1/stores/{storeId}/reviews/{reviewId}")
    public ApiResponse<ReviewDetailResponse> updateReview(@RequestBody UpdateReviewRequest request, @PathVariable Long storeId, @PathVariable Long reviewId) {
        //인증 필요
        return ApiResponse.success(reviewService.updateReview(request, 1L, reviewId));
    }

    @DeleteMapping("/v1/stores/{storeId}/reviews/{reviewId}")
    public ApiResponse<Object> deleteReview(@PathVariable Long storeId, @PathVariable Long reviewId) {
        //인증 필요
        reviewService.deleteReview(1L, reviewId);
        return ApiResponse.success(null);
    }

    @PostMapping("/v1/stores/{storeId}/reviews/{reviewId}/like")
    public ApiResponse<Object> likeReview(@PathVariable Long storeId, @PathVariable Long reviewId) {
        return ApiResponse.success(null);
    }
}
