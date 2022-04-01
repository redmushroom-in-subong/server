package com.rms.drifeserver.domain.review.service;

import com.rms.drifeserver.domain.review.service.dto.response.ReviewKeywordCountResponse;
import com.rms.drifeserver.domain.store.model.Store;

import java.util.List;

public interface ReviewKeywordService {

    List<ReviewKeywordCountResponse> getReviewKeywordCountByStore(Store store);
}
