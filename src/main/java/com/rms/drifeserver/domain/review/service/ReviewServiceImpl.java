package com.rms.drifeserver.domain.review.service;

import com.rms.drifeserver.domain.review.dao.ReviewRepository;
import com.rms.drifeserver.domain.review.service.dto.request.AddReviewRequest;
import com.rms.drifeserver.domain.review.service.dto.request.UpdateReviewRequest;
import com.rms.drifeserver.domain.review.service.dto.response.ReviewDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    @Override
    public void addReview(AddReviewRequest request, Long userId, Long storeId) {

    }

    @Override
    public ReviewDetailResponse getReviewDetail(Long storeId, Long reviewId) {
        return null;
    }

    @Override
    public ReviewDetailResponse updateReview(UpdateReviewRequest request, Long userId, Long storeId, Long reviewId) {
        return null;
    }

    @Override
    public void deleteReview(Long userId, Long storeId, Long reviewId) {

    }
}
