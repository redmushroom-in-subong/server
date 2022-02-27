package com.rms.drifeserver.domain.review.service;

import com.rms.drifeserver.domain.review.service.dto.response.ReviewKeywordTypeResponse;

import java.util.List;

public interface ReviewKeywordTypeService {
    List<ReviewKeywordTypeResponse> getReviewKeywordTypes();
}
