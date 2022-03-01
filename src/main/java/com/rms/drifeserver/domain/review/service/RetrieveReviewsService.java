package com.rms.drifeserver.domain.review.service;

import com.rms.drifeserver.domain.review.service.dto.response.ReviewsResponse;

public interface RetrieveReviewsService {
    ReviewsResponse getAllReviewsInStore(Long storeId);
    ReviewsResponse getAllReviewsInStoreWithUserId(Long storeId, Long userId);
    ReviewsResponse getAllReviewsInStoreWithKeywordId(Long storeId, Long keywordId);
}
