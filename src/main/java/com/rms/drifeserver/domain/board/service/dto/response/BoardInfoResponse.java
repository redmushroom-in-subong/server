package com.rms.drifeserver.domain.board.service.dto.response;

import com.rms.drifeserver.domain.board.model.Board;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardInfoResponse {

    private Long boardId;
    private String title;
    private String contents;
    private Long commentCount;
    private Long likeCount;
    private LocalDateTime createdAt;

    public static BoardInfoResponse of(Board board, Long commentCount ,Long likeCount) {
        return new BoardInfoResponse(board.getId(), board.getTitle(), board.getContents(), commentCount,
                likeCount, board.getCreatedAt());
    }
}
