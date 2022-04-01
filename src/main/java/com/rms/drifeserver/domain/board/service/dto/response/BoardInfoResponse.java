package com.rms.drifeserver.domain.board.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BoardInfoResponse {

    private Long boardId;
    private String title;
    private String context;
    private Long commentCount;
    private Long likeCount;
    private LocalDateTime createdAt;
}
