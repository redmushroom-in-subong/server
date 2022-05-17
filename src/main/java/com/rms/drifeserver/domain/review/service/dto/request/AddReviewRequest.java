package com.rms.drifeserver.domain.review.service.dto.request;

import com.rms.drifeserver.domain.review.model.Review;
import com.rms.drifeserver.domain.store.model.Store;
import com.rms.drifeserver.domain.user.model.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddReviewRequest {

    private Long storeId;
    @Size(max = 300, message = "{review.content.size}")
    @NotBlank(message = "{review.content.notBlank}")
    private String contents;

    private List<Long> keywordIds;

    private List<String> imageUrls;

    public Review toReview(User user, Store store) {
        return Review.of(user, store, this.contents, this.keywordIds, this.imageUrls);
    }
}
