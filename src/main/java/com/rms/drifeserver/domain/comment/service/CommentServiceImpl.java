package com.rms.drifeserver.domain.comment.service;

import com.rms.drifeserver.domain.board.model.Board;
import com.rms.drifeserver.domain.board.repository.BoardRepository;
import com.rms.drifeserver.domain.comment.model.Comment;
import com.rms.drifeserver.domain.comment.repository.CommentRepository;
import com.rms.drifeserver.domain.comment.service.dto.request.AddCommentRequest;
import com.rms.drifeserver.domain.comment.service.dto.request.UpdateCommentRequest;
import com.rms.drifeserver.domain.comment.service.dto.response.CommentInfoResponse;
import com.rms.drifeserver.domain.common.exception.BaseException;
import com.rms.drifeserver.domain.common.exception.type.ErrorCode;
import com.rms.drifeserver.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Transactional
    @Override
    public void addComment(AddCommentRequest request, Long boardId, User user) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_BOARD));

        Comment comment = request.toComment(board, user);

        commentRepository.save(comment);
    }

    @Override
    public CommentInfoResponse updateComment(UpdateCommentRequest request, Long commentId, User user) {
        Comment comment = commentRepository.findByIdAndUser(commentId, user)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_COMMENT));

        comment.update(request.getContents());

        return CommentInfoResponse.of(comment, 0L);
    }

    @Override
    public void deleteComment(Long commentId, User user) {
        Comment comment = commentRepository.findByIdAndUser(commentId, user)
                .orElseThrow(() -> new BaseException(ErrorCode.NOTFOUND_COMMENT));

        commentRepository.delete(comment);
    }
}
