package com.rms.drifeserver.domain.review.service.dto.response;

import com.rms.drifeserver.domain.review.model.ReviewKeywordType;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewKeywordTypeResponse {

    private Long id;
    private String name;

    public static ReviewKeywordTypeResponse of(ReviewKeywordType reviewKeywordType) {
        return new ReviewKeywordTypeResponse(reviewKeywordType.getId(), reviewKeywordType.getName());
    }
}
