package com.rms.drifeserver.domain.board.service.dto.response;

import com.rms.drifeserver.domain.comment.service.dto.response.CommentInfoResponse;
import com.rms.drifeserver.domain.user.service.dto.response.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class BoardDetailResponse {

    private UserInfo userInfo;
    private Long boardId;
    private String title;
    private String contents;
    private List<CommentInfoResponse> comments;
    private Long likeCount;
    private LocalDateTime createdAt;
}
