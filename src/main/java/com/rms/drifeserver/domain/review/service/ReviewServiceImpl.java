package com.rms.drifeserver.domain.review.service;

import com.rms.drifeserver.domain.review.dao.ReviewRepository;
import com.rms.drifeserver.domain.review.service.dto.response.ReviewDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    @Override
    public ReviewDetailResponse getReviewDetail(Long userId, Long storeId, Long reviewId) {
        return null;
    }
}
