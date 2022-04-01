package com.rms.drifeserver.domain.review.service.dto.response;

import com.rms.drifeserver.domain.review.model.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ReviewsResponse {

    private List<ReviewDetailResponse> results;

    public static ReviewsResponse of(List<ReviewDetailResponse> results) {
        return new ReviewsResponse(results);
    }
}
