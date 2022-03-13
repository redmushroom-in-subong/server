package com.rms.drifeserver.domain.review.service;

import com.rms.drifeserver.domain.review.dao.ReviewRepository;
import com.rms.drifeserver.domain.review.service.dto.response.ReviewsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetrieveReviewsServiceImpl implements RetrieveReviewsService{
    private final ReviewRepository reviewRepository;

    @Override
    public ReviewsResponse getAllReviewsInStore(Long storeId) {
        return ReviewsResponse.of(reviewRepository.findAllByStoreId(storeId));
    }

    @Override
    public ReviewsResponse getAllReviewsInStoreWithUserId(Long storeId, Long userId) {
        return ReviewsResponse.of(reviewRepository.findAllByStoreIdAndUserId(storeId, userId));
    }

    @Override
    public ReviewsResponse getAllReviewsInStoreWithKeywordId(Long storeId, Long keywordId) {
        return ReviewsResponse.of(reviewRepository.findAllByStoreIdAndKeywordTypeId(storeId, keywordId));
    }
}
