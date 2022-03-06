package com.rms.drifeserver.domain.review.service.dto.request;

import com.rms.drifeserver.domain.review.model.Review;
import com.rms.drifeserver.domain.review.model.ReviewKeywordType;
import com.rms.drifeserver.domain.store.model.Store;
import com.rms.drifeserver.domain.user.model.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddReviewRequest {

    private String contents;

    private List<Long> keywordIds;

    private List<String> images;

    public Review toReview(User user, Store store) {
        return Review.of(user, store, this.contents);
    }
}
