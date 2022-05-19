package com.rms.drifeserver.domain.review.service.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateReviewRequest {

    private String contents;

    private List<Long> keywordIds;

    private List<String> images;

    //test용 생성자
    public UpdateReviewRequest (String contents, List<Long> keywordIds) {
        this.contents = contents;
        this.keywordIds = keywordIds;
    }

}
