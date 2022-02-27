package com.rms.drifeserver.domain.review.service;

import com.rms.drifeserver.domain.review.service.dto.request.UpdateReviewRequest;
import com.rms.drifeserver.domain.review.service.dto.response.ReviewDetailResponse;

public interface ReviewService {

    ReviewDetailResponse getReviewDetail(Long storeId, Long reviewId);
    ReviewDetailResponse updateReview(UpdateReviewRequest request, Long storeId, Long reviewId);
    void deleteReview(Long storeId, Long reviewId);
}
