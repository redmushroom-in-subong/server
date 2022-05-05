package com.rms.drifeserver.domain.review.service;

import com.rms.drifeserver.domain.review.service.dto.response.ReviewsResponse;
import org.springframework.data.domain.Pageable;

public interface RetrieveReviewsService {
    ReviewsResponse getAllReviewsInStore(Long storeId, Pageable pageable);
    ReviewsResponse getAllReviewsInStoreWithUserId(Long storeId, Long userId, Pageable pageable);
    ReviewsResponse getAllReviewsInStoreWithKeywordId(Long storeId, Long keywordId, Pageable pageable);
}
