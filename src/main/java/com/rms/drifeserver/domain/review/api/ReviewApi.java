package com.rms.drifeserver.domain.review.api;

import com.rms.drifeserver.domain.common.dto.ApiResponse;
import com.rms.drifeserver.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewApi {
    private final ReviewService reviewService;

    @PostMapping("/v1/stores/{storeId}/reviews")
    public ApiResponse<Object> addReview(@PathVariable Long storeId) {
        return ApiResponse.success(null);
    }

    @GetMapping("/v1/stores/{storeId}/reviews/{reviewId}")
    public ApiResponse<Object> getReview(@PathVariable Long storeId, @PathVariable Long reviewId) {
        return ApiResponse.success(null);
    }

    @PutMapping("/v1/stores/{storeId}/reviews/{reviewId}")
    public ApiResponse<Object> updateReview(@PathVariable Long storeId, @PathVariable Long reviewId) {
        return ApiResponse.success(null);
    }

    @DeleteMapping("/v1/stores/{storeId}/reviews/{reviewId}")
    public ApiResponse<Object> deleteReview(@PathVariable Long storeId, @PathVariable Long reviewId) {
        return ApiResponse.success(null);
    }

    @PostMapping("/v1/stores/{storeId}/reviews/{reviewId}/like")
    public ApiResponse<Object> likeReview(@PathVariable Long storeId, @PathVariable Long reviewId) {
        return ApiResponse.success(null);
    }
}
