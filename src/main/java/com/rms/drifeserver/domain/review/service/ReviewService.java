package com.rms.drifeserver.domain.review.service;

import com.rms.drifeserver.domain.review.service.dto.response.ReviewDetailResponse;

public interface ReviewService {

    ReviewDetailResponse getReviewDetail(Long userId, Long storeId, Long reviewId);
}
