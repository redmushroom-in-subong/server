package com.rms.drifeserver.domain.board.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UpdateBoardRequest {

    private String title;

    private String contents;

    private List<String> imageUrls;
}
