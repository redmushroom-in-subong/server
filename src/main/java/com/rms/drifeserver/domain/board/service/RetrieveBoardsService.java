package com.rms.drifeserver.domain.board.service;

import com.rms.drifeserver.domain.board.service.dto.response.BoardsResponse;
import com.rms.drifeserver.domain.user.model.User;

public interface RetrieveBoardsService {

    BoardsResponse getBoards(User user);

    BoardsResponse getHotBoards(User user);
}
