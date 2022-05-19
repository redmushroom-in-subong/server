package com.rms.drifeserver.domain.review.service;

import com.rms.drifeserver.domain.like.dao.ReviewLikesRepository;
import com.rms.drifeserver.domain.review.dao.ReviewRepository;
import com.rms.drifeserver.domain.review.dao.VisitRepository;
import com.rms.drifeserver.domain.review.model.Review;
import com.rms.drifeserver.domain.review.service.dto.response.ReviewDetailResponse;
import com.rms.drifeserver.domain.review.service.dto.response.ReviewsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RetrieveReviewsServiceImpl implements RetrieveReviewsService{
    private final VisitRepository visitRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewLikesRepository reviewLikesRepository;

    @Transactional(readOnly = true)
    @Override
    public ReviewsResponse getAllReviewsInStore(Long storeId, Pageable pageable) {
        List<Review> reviews = reviewRepository.findAllByStoreId(storeId, pageable);
        List<ReviewDetailResponse> reviewDetailResponses = reviews.stream()
                .map(review -> ReviewDetailResponse.of(review, ReviewServiceUtils.getReviewCount(visitRepository,
                        reviewRepository, reviewLikesRepository, review)))
                .collect(Collectors.toList());
        return ReviewsResponse.of(reviewDetailResponses);
    }

    @Transactional(readOnly = true)
    @Override
    public ReviewsResponse getAllReviewsInStoreWithUserId(Long storeId, Long userId, Pageable pageable) {
        List<Review> reviews = reviewRepository.findAllByStoreIdAndUserId(storeId, userId, pageable);
        List<ReviewDetailResponse> reviewDetailResponses = reviews.stream()
                .map(review -> ReviewDetailResponse.of(review, ReviewServiceUtils.getReviewCount(visitRepository,
                        reviewRepository, reviewLikesRepository, review)))
                .collect(Collectors.toList());
        return ReviewsResponse.of(reviewDetailResponses);
    }

    @Transactional(readOnly = true)
    @Override
    public ReviewsResponse getAllReviewsInStoreWithKeywordId(Long storeId, Long keywordId, Pageable pageable) {
        List<Review> reviews = reviewRepository.findAllByStoreIdAndKeywordTypeId(storeId, keywordId, pageable);
        List<ReviewDetailResponse> reviewDetailResponses = reviews.stream()
                .map(review -> ReviewDetailResponse.of(review, ReviewServiceUtils.getReviewCount(visitRepository,
                        reviewRepository, reviewLikesRepository, review)))
                .collect(Collectors.toList());
        return ReviewsResponse.of(reviewDetailResponses);
    }
}
