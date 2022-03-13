package com.rms.drifeserver.domain.review.service;

import com.rms.drifeserver.domain.common.exception.BaseException;
import com.rms.drifeserver.domain.review.service.dto.request.AddReviewRequest;
import com.rms.drifeserver.domain.review.service.dto.request.UpdateReviewRequest;
import com.rms.drifeserver.domain.review.service.dto.response.ReviewDetailResponse;

public interface ReviewService {

    void addReview(AddReviewRequest request, Long userId, Long storeId) throws BaseException;
    ReviewDetailResponse getReviewDetail(Long reviewId);
    ReviewDetailResponse updateReview(UpdateReviewRequest request, Long userId, Long reviewId);
    void deleteReview(Long userId, Long reviewId);
}
