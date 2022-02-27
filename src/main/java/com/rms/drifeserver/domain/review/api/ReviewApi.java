package com.rms.drifeserver.domain.review.api;

import com.rms.drifeserver.domain.common.dto.ApiResponse;
import com.rms.drifeserver.domain.review.service.ReviewService;
import com.rms.drifeserver.domain.review.service.dto.request.AddReviewRequest;
import com.rms.drifeserver.domain.review.service.dto.request.UpdateReviewRequest;
import com.rms.drifeserver.domain.review.service.dto.response.ReviewDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewApi {
    private final ReviewService reviewService;

    @PostMapping("/v1/stores/{storeId}/reviews")
    public ApiResponse<Object> addReview(@RequestBody AddReviewRequest request, @PathVariable Long storeId) {
        //인증 필요
        reviewService.addReview(request, 1L, storeId);
        return ApiResponse.success(null);
    }

    @GetMapping("/v1/stores/{storeId}/reviews/{reviewId}")
    public ApiResponse<ReviewDetailResponse> getReview(@PathVariable Long storeId, @PathVariable Long reviewId) {
        return ApiResponse.success(reviewService.getReviewDetail( storeId, reviewId));
    }

    @PutMapping("/v1/stores/{storeId}/reviews/{reviewId}")
    public ApiResponse<ReviewDetailResponse> updateReview(@RequestBody UpdateReviewRequest request, @PathVariable Long storeId, @PathVariable Long reviewId) {
        //인증 필요
        return ApiResponse.success(reviewService.updateReview(request, 1L,storeId, reviewId));
    }

    @DeleteMapping("/v1/stores/{storeId}/reviews/{reviewId}")
    public ApiResponse<Object> deleteReview(@PathVariable Long storeId, @PathVariable Long reviewId) {
        //인증 필요
        reviewService.deleteReview(1L, storeId, reviewId);
        return ApiResponse.success(null);
    }

    @PostMapping("/v1/stores/{storeId}/reviews/{reviewId}/like")
    public ApiResponse<Object> likeReview(@PathVariable Long storeId, @PathVariable Long reviewId) {
        return ApiResponse.success(null);
    }
}
