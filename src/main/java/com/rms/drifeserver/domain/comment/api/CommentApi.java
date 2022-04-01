package com.rms.drifeserver.domain.comment.api;

import com.rms.drifeserver.domain.comment.service.CommentService;
import com.rms.drifeserver.domain.comment.service.dto.request.AddCommentRequest;
import com.rms.drifeserver.domain.comment.service.dto.request.UpdateCommentRequest;
import com.rms.drifeserver.domain.common.dto.ApiResponse;
import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentApi {
    private final UserService userService;
    private final CommentService commentService;

    @PostMapping("/v1/boards/{boardId}/comments")
    public ApiResponse<Object> addComment(@RequestBody AddCommentRequest request, @PathVariable Long boardId) {

        User user = userService.getUserEntity();

        commentService.addComment(request, boardId, user);

        return ApiResponse.success(null);
    }

    @PutMapping("/v1/boards/{boardId}/comments/{commentId}")
    public ApiResponse<Object> updateComment(@RequestBody UpdateCommentRequest request, @PathVariable Long boardId, @PathVariable Long commentId) {

        User user = userService.getUserEntity();

        return ApiResponse.success(commentService.updateComment(request, commentId, user));
    }

    @DeleteMapping("/v1/boards/{boardId}/comments/{commentId}")
    public ApiResponse<Object> deleteComment(@PathVariable Long boardId, @PathVariable Long commentId) {

        User user = userService.getUserEntity();

        commentService.deleteComment(commentId, user);

        return ApiResponse.success(null);
    }

    @PostMapping("/v1/boards/{boardId}/comments/{commentId}/like")
    public ApiResponse<Object> likeComment(@PathVariable Long boardId, @PathVariable Long commentId) {

        User user = userService.getUserEntity();

        commentService.toggleCommentLike(commentId, user);

        return ApiResponse.success(null);
    }
}
