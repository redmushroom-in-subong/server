package com.rms.drifeserver.domain.board.service;

import com.rms.drifeserver.domain.board.service.dto.request.AddBoardRequest;
import com.rms.drifeserver.domain.board.service.dto.request.UpdateBoardRequest;
import com.rms.drifeserver.domain.board.service.dto.response.BoardDetailResponse;
import com.rms.drifeserver.domain.user.model.User;

public interface BoardService {

    void addBoard(AddBoardRequest request, User user);

    BoardDetailResponse getBoardDetail(Long boardId);

    BoardDetailResponse updateBoard(UpdateBoardRequest request, Long boardId, User user);

    void deleteBoard(Long boardId, User user);

    void toggleBoardLike(Long boardId, User user);
}
