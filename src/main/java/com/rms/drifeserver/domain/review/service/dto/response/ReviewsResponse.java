package com.rms.drifeserver.domain.review.service.dto.response;

import com.rms.drifeserver.domain.review.model.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class ReviewsResponse {

    private List<ReviewDetailResponse> results = new ArrayList<>();

    public static ReviewsResponse of (List<Review> reviews) {
        List<ReviewDetailResponse> results = reviews.stream()
                .map(ReviewDetailResponse::of)
                .collect(Collectors.toList());
        return new ReviewsResponse(results);
    }
}
