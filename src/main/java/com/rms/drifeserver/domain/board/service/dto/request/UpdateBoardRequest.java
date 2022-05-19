package com.rms.drifeserver.domain.board.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@NoArgsConstructor
public class UpdateBoardRequest {

    @NotBlank(message = "게시글 제목이 비어있습니다.")
    private String title;

    @NotBlank(message = "게시글 내용이 비어있습니다.")
    private String contents;

    private List<String> imageUrls;
}
