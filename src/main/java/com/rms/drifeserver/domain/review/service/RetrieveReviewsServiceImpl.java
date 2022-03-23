package com.rms.drifeserver.domain.review.service;

import com.rms.drifeserver.domain.review.dao.ReviewRepository;
import com.rms.drifeserver.domain.review.dao.VisitRepository;
import com.rms.drifeserver.domain.review.model.Review;
import com.rms.drifeserver.domain.review.service.dto.response.ReviewDetailResponse;
import com.rms.drifeserver.domain.review.service.dto.response.ReviewsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RetrieveReviewsServiceImpl implements RetrieveReviewsService{
    private final VisitRepository visitRepository;
    private final ReviewRepository reviewRepository;

    @Transactional(readOnly = true)
    @Override
    public ReviewsResponse getAllReviewsInStore(Long storeId) {
        List<Review> reviews = reviewRepository.findAllByStoreId(storeId);
        List<ReviewDetailResponse> reviewDetailResponses = reviews.stream()
                .map(review -> ReviewDetailResponse.of(review, ReviewServiceUtils.findCount(visitRepository, reviewRepository, review)))
                .collect(Collectors.toList());
        return ReviewsResponse.of(reviewDetailResponses);
    }

    @Transactional(readOnly = true)
    @Override
    public ReviewsResponse getAllReviewsInStoreWithUserId(Long storeId, Long userId) {
        List<Review> reviews = reviewRepository.findAllByStoreIdAndUserId(storeId, userId);
        List<ReviewDetailResponse> reviewDetailResponses = reviews.stream()
                .map(review -> ReviewDetailResponse.of(review, ReviewServiceUtils.findCount(visitRepository, reviewRepository, review)))
                .collect(Collectors.toList());
        return ReviewsResponse.of(reviewDetailResponses);
    }

    @Transactional(readOnly = true)
    @Override
    public ReviewsResponse getAllReviewsInStoreWithKeywordId(Long storeId, Long keywordId) {
        List<Review> reviews = reviewRepository.findAllByStoreIdAndKeywordTypeId(storeId, keywordId);
        List<ReviewDetailResponse> reviewDetailResponses = reviews.stream()
                .map(review -> ReviewDetailResponse.of(review, ReviewServiceUtils.findCount(visitRepository, reviewRepository, review)))
                .collect(Collectors.toList());
        return ReviewsResponse.of(reviewDetailResponses);
    }
}
