package com.rms.drifeserver.domain.review.service.dto.request;

import com.rms.drifeserver.domain.review.model.Review;
import com.rms.drifeserver.domain.store.model.Store;
import com.rms.drifeserver.domain.user.model.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddReviewRequest {

    @NotNull(message = "가게 아이디가 없습니다.")
    private Long storeId;
    @Size(max = 300, message = "리뷰 글자수가 300자를 초과하였습니다.")
    @NotBlank(message = "리뷰 내용이 비어있습니다.")
    private String contents;

    private List<Long> keywordIds;

    @Size(max = 10, message = "사진은 최대 10장까지만 가능합니다.")
    private List<String> imageUrls;

    public Review toReview(User user, Store store) {
        return Review.of(user, store, this.contents, this.keywordIds, this.imageUrls);
    }
}
