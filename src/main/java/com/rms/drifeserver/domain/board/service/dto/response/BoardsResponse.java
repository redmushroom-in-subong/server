package com.rms.drifeserver.domain.board.service.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardsResponse {
    private List<BoardInfoResponse> boardInfoResponses;

    public static BoardsResponse of(List<BoardInfoResponse> boardInfoResponses) {
        return new BoardsResponse(boardInfoResponses);
    }
}
