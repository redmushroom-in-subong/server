package com.rms.drifeserver.domain.board.service;

import com.rms.drifeserver.domain.board.model.Board;
import com.rms.drifeserver.domain.board.dao.BoardRepository;
import com.rms.drifeserver.domain.board.service.dto.request.AddBoardRequest;
import com.rms.drifeserver.domain.board.service.dto.request.UpdateBoardRequest;
import com.rms.drifeserver.domain.board.service.dto.response.BoardDetailResponse;
import com.rms.drifeserver.domain.comment.service.dto.response.CommentInfoResponse;
import com.rms.drifeserver.domain.common.exception.BaseException;
import com.rms.drifeserver.domain.common.exception.type.ErrorCode;
import com.rms.drifeserver.domain.like.dao.BoardLikesRepository;
import com.rms.drifeserver.domain.like.dao.CommentLikesRepository;
import com.rms.drifeserver.domain.like.model.BoardLikes;
import com.rms.drifeserver.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final BoardLikesRepository boardLikesRepository;
    private final CommentLikesRepository commentLikesRepository;

    @Transactional
    @Override
    public void addBoard(AddBoardRequest request, User user) {
        Board board = request.toBoard(user);

        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    @Override
    public BoardDetailResponse getBoardDetail(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_BOARD));

        List<CommentInfoResponse> commentInfos = board.getComments().stream()
                .map(comment -> CommentInfoResponse.of(comment, commentLikesRepository.countByComment(comment)))
                .collect(Collectors.toList());

        return BoardDetailResponse.of(board, commentInfos, boardLikesRepository.countByBoard(board));
    }

    @Transactional
    @Override
    public BoardDetailResponse updateBoard(UpdateBoardRequest request, Long boardId, User user) {
        Board board = boardRepository.findByIdAndUser(boardId, user)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_BOARD));

        board.update(request.getTitle(), request.getContents(), request.getImageUrls());

        List<CommentInfoResponse> commentInfos = board.getComments().stream()
                .map(comment -> CommentInfoResponse.of(comment, commentLikesRepository.countByComment(comment)))
                .collect(Collectors.toList());

        return BoardDetailResponse.of(board, commentInfos, boardLikesRepository.countByBoard(board));
    }

    @Transactional
    @Override
    public void deleteBoard(Long boardId, User user) {
        Board board = boardRepository.findByIdAndUser(boardId, user)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_BOARD));
        boardRepository.delete(board);
    }

    @Transactional
    @Override
    public void toggleBoardLike(Long boardId, User user) {
        Board board = boardRepository.getById(boardId);
        BoardLikes boardLikes = boardLikesRepository.findByUserAndBoard(user, board);

        if (boardLikes != null) {
            boardLikesRepository.delete(boardLikes);
        } else {
            boardLikesRepository.save(BoardLikes.of(user, board));
        }
    }
}
