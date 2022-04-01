package com.rms.drifeserver.domain.board.service;

import com.rms.drifeserver.domain.board.service.dto.request.AddBoardRequest;
import com.rms.drifeserver.domain.board.service.dto.request.UpdateBoardRequest;
import com.rms.drifeserver.domain.board.service.dto.response.BoardDetailResponse;
import com.rms.drifeserver.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    @Override
    public void addBoard(AddBoardRequest request, User user) {

    }

    @Override
    public BoardDetailResponse getBoardDetail(Long boardId) {
        return null;
    }

    @Override
    public BoardDetailResponse updateBoard(UpdateBoardRequest request, Long BoardId, User user) {
        return null;
    }

    @Override
    public void deleteBoard(Long BoardId, User user) {

    }
}
