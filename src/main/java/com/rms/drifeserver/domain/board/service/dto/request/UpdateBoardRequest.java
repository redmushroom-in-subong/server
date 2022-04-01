package com.rms.drifeserver.domain.board.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateBoardRequest {

    private String title;

    private String contents;
}
