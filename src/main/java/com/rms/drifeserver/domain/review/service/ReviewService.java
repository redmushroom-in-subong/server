package com.rms.drifeserver.domain.review.service;

import com.rms.drifeserver.domain.review.service.dto.request.AddReviewRequest;
import com.rms.drifeserver.domain.review.service.dto.request.UpdateReviewRequest;
import com.rms.drifeserver.domain.review.service.dto.response.ReviewDetailResponse;

public interface ReviewService {

    void addReview(AddReviewRequest request, Long userId, Long storeId);
    ReviewDetailResponse getReviewDetail(Long storeId, Long reviewId);
    ReviewDetailResponse updateReview(UpdateReviewRequest request, Long userId, Long storeId, Long reviewId);
    void deleteReview(Long userId, Long storeId, Long reviewId);
}
