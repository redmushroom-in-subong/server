package com.rms.drifeserver.domain.comment.service.dto.request;

import com.rms.drifeserver.domain.board.model.Board;
import com.rms.drifeserver.domain.comment.model.Comment;
import com.rms.drifeserver.domain.user.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class AddCommentRequest {

    @NotNull(message = "게시글 아이디가 없습니다.")
    private  Long boardId;

    @NotBlank(message = "댓글 내용이 비어있습니다.")
    private String contents;

    public Comment toComment(Board board, User user) {
        return Comment.of(board, user, this.contents);
    }
}
