package com.rms.drifeserver.domain.review.service.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateReviewRequest {

    @Size(max = 300, message = "{review.content.size}")
    @NotBlank(message = "{review.content.notBlank}")
    private String contents;

    private List<Long> keywordIds;

    @Size(max = 10, message = "사진은 최대 10장까지만 가능합니다.")
    private List<String> imageUrls;

    //test용 생성자
    public UpdateReviewRequest (String contents, List<Long> keywordIds) {
        this.contents = contents;
        this.keywordIds = keywordIds;
    }

}
