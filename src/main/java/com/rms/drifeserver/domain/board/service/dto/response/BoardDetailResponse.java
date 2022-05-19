package com.rms.drifeserver.domain.board.service.dto.response;

import com.rms.drifeserver.domain.board.model.Board;
import com.rms.drifeserver.domain.comment.service.dto.response.CommentInfoResponse;
import com.rms.drifeserver.domain.user.service.dto.response.UserInfo;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardDetailResponse {

    private UserInfo userInfo;
    private Long boardId;
    private String title;
    private String contents;
    private List<String> images;
    private List<CommentInfoResponse> comments;
    private Long likeCount;
    private LocalDateTime createdAt;

    @Builder(access = AccessLevel.PRIVATE)
    private BoardDetailResponse(UserInfo userInfo, Long boardId, String title, String contents, List<String> images,
                                List<CommentInfoResponse> comments, Long likeCount, LocalDateTime createdAt) {
        this.userInfo = userInfo;
        this.boardId = boardId;
        this.title = title;
        this.contents = contents;
        this.images = images;
        this.comments = comments;
        this.likeCount = likeCount;
        this.createdAt = createdAt;
    }

    public static BoardDetailResponse of(Board board, List<CommentInfoResponse> comments, Long likeCount) {
        BoardDetailResponse response = BoardDetailResponse.builder()
                .userInfo(UserInfo.of(board.getUser()))
                .boardId(board.getId())
                .title(board.getTitle())
                .contents(board.getContents())
                .images(board.getBoardImages().stream()
                    .map(boardImage -> boardImage.getImageUrl())
                    .collect(Collectors.toList()))
                .comments(comments)
                .likeCount(likeCount)
                .createdAt(board.getCreatedAt())
                .build();
        return response;
    }
}
