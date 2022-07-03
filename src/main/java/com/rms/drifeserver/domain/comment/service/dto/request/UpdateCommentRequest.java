package com.rms.drifeserver.domain.comment.service.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class UpdateCommentRequest {

    @NotBlank(message = "댓글 내용이 비어있습니다.")
    String contents;

    @Builder(builderMethodName = "testBuilder")
    private UpdateCommentRequest(String contents) {
        this.contents = contents;
    }
}
