package com.rms.drifeserver.domain.board.service;

import com.rms.drifeserver.domain.board.model.Board;
import com.rms.drifeserver.domain.board.dao.BoardRepository;
import com.rms.drifeserver.domain.board.service.dto.response.BoardInfoResponse;
import com.rms.drifeserver.domain.board.service.dto.response.BoardsResponse;
import com.rms.drifeserver.domain.comment.dao.CommentRepository;
import com.rms.drifeserver.domain.like.dao.BoardLikesRepository;
import com.rms.drifeserver.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RetrieveBoardsServiceImpl implements RetrieveBoardsService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final BoardLikesRepository boardLikesRepository;

    @Transactional(readOnly = true)
    @Override
    public BoardsResponse getBoards(User user, Pageable pageable) {
        List<Board> boards = boardRepository.findByCodeOrderByCreatedAtDesc(user.getRegionCode(), pageable);

        List<BoardInfoResponse> boardInfoResponses = boards.stream()
                .map(board -> BoardInfoResponse.of(board, commentRepository.countByBoard(board),
                        boardLikesRepository.countByBoard(board)))
                .collect(Collectors.toList());

        return BoardsResponse.of(boardInfoResponses);
    }

    @Transactional(readOnly = true)
    @Override
    public BoardsResponse getHotBoards(User user, Pageable pageable) {
        List<Board> boards = boardRepository.findHotBoards(user.getRegionCode(), pageable);

        List<BoardInfoResponse> boardInfoResponses = boards.stream()
                .map(board -> BoardInfoResponse.of(board, commentRepository.countByBoard(board),
                        boardLikesRepository.countByBoard(board)))
                .collect(Collectors.toList());

        return BoardsResponse.of(boardInfoResponses);
    }

    @Transactional(readOnly = true)
    @Override
    public BoardsResponse getBoardsByKeyword(User user, String keyword, Pageable pageable) {
        List<Board> boards = boardRepository.findBoardsByKeyword(user.getRegionCode(), keyword, pageable);

        List<BoardInfoResponse> boardInfoResponses = boards.stream()
                .map(board -> BoardInfoResponse.of(board, commentRepository.countByBoard(board),
                        boardLikesRepository.countByBoard(board)))
                .collect(Collectors.toList());

        return BoardsResponse.of(boardInfoResponses);
    }
}
