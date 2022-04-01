package com.rms.drifeserver.domain.comment.service.dto.response;

import com.rms.drifeserver.domain.user.service.dto.response.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentInfoResponse {
    private UserInfo userInfo;
    private Long commentId;
    private String contents;
    private Long likeCount;
    private LocalDateTime createdAt;
}
