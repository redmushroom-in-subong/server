package com.rms.drifeserver.domain.comment.service.dto.request;

import com.rms.drifeserver.domain.board.model.Board;
import com.rms.drifeserver.domain.comment.model.Comment;
import com.rms.drifeserver.domain.user.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddCommentRequest {
    private String contents;

    public Comment toComment(Board board, User user) {
        return Comment.of(board, user, this.contents);
    }
}
