package com.rms.drifeserver.domain.user.service;

import com.rms.drifeserver.domain.user.service.dto.response.MyReviewResponse;
import com.rms.drifeserver.domain.user.service.dto.response.MyStoreResponse;

import java.util.List;

public interface MyReviewService {
    List<MyReviewResponse> getMyReviews();
}

