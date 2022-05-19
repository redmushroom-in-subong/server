package com.rms.drifeserver.domain.comment.service.dto.response;

import com.rms.drifeserver.domain.comment.model.Comment;
import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.domain.user.service.dto.response.UserInfo;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentInfoResponse {
    private UserInfo userInfo;
    private Long commentId;
    private String contents;
    private Long likeCount;
    private LocalDateTime createdAt;

    @Builder
    private CommentInfoResponse(UserInfo userInfo, Long commentId, String contents, Long likeCount, LocalDateTime createdAt) {
        this.userInfo = userInfo;
        this.commentId = commentId;
        this.contents = contents;
        this.likeCount = likeCount;
        this.createdAt = createdAt;
    }

    public static CommentInfoResponse of(Comment comment, Long likeCount) {
        CommentInfoResponse response = CommentInfoResponse.builder()
                .userInfo(UserInfo.of(comment.getUser()))
                .commentId(comment.getId())
                .contents(comment.getContents())
                .likeCount(likeCount)
                .createdAt(comment.getCreatedAt())
                .build();

        return response;
    }
}
