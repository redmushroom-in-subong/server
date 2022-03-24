package com.rms.drifeserver.domain.review.service;

import com.rms.drifeserver.domain.common.exception.BaseException;
import com.rms.drifeserver.domain.review.service.dto.request.AddReviewRequest;
import com.rms.drifeserver.domain.review.service.dto.request.UpdateReviewRequest;
import com.rms.drifeserver.domain.review.service.dto.response.ReviewDetailResponse;
import com.rms.drifeserver.domain.user.model.User;

public interface ReviewService {

    void addReview(AddReviewRequest request, Long storeId, User user) throws BaseException;

    ReviewDetailResponse getReviewDetail(Long reviewId);

    ReviewDetailResponse updateReview(UpdateReviewRequest request, Long reviewId, User user);

    void deleteReview(Long reviewId, User user);

    void likeReview(Long reviewId, User user);
}
