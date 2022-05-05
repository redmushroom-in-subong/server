package com.rms.drifeserver.domain.board.service;

import com.rms.drifeserver.domain.board.service.dto.response.BoardsResponse;
import com.rms.drifeserver.domain.user.model.User;
import org.springframework.data.domain.Pageable;

public interface RetrieveBoardsService {

    BoardsResponse getBoards(User user, Pageable pageable);

    BoardsResponse getHotBoards(User user, Pageable pageable);
}
