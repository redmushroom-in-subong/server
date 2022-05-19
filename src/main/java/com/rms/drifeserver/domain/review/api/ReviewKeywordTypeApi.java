package com.rms.drifeserver.domain.review.api;

import com.rms.drifeserver.domain.common.dto.ApiResponse;
import com.rms.drifeserver.domain.review.service.ReviewKeywordTypeService;
import com.rms.drifeserver.domain.review.service.dto.response.ReviewKeywordTypeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewKeywordTypeApi {

    private final ReviewKeywordTypeService reviewKeywordTypeService;

    @GetMapping("/v1/review-keyword-types")
    public ApiResponse<List<ReviewKeywordTypeResponse>> getAllReviewKeywordTypes() {
        return ApiResponse.success(reviewKeywordTypeService.getReviewKeywordTypes());
    }
}
