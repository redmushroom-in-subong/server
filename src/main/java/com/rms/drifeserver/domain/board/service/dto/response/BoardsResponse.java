package com.rms.drifeserver.domain.board.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BoardsResponse {
    private List<BoardInfoResponse> boardInfoResponses;
}
