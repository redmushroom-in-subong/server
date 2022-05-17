package com.rms.drifeserver.domain.comment.api;

import com.rms.drifeserver.domain.comment.service.CommentService;
import com.rms.drifeserver.domain.comment.service.dto.request.AddCommentRequest;
import com.rms.drifeserver.domain.comment.service.dto.request.UpdateCommentRequest;
import com.rms.drifeserver.domain.common.dto.ApiResponse;
import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CommentApi {
    private final UserService userService;
    private final CommentService commentService;

    @PostMapping("/v1/board/comments")
    public ApiResponse<Object> addComment(@Valid @RequestBody AddCommentRequest request) {

        User user = userService.getUserEntity();

        commentService.addComment(request, user);

        return ApiResponse.success(null);
    }

    @PutMapping("/v1/board/comments/{commentId}")
    public ApiResponse<Object> updateComment(@Valid @RequestBody UpdateCommentRequest request, @PathVariable Long commentId) {

        User user = userService.getUserEntity();

        return ApiResponse.success(commentService.updateComment(request, commentId, user));
    }

    @DeleteMapping("/v1/board/comments/{commentId}")
    public ApiResponse<Object> deleteComment(@PathVariable Long commentId) {

        User user = userService.getUserEntity();

        commentService.deleteComment(commentId, user);

        return ApiResponse.success(null);
    }

    @PostMapping("/v1/board/comments/{commentId}/like")
    public ApiResponse<Object> likeComment(@PathVariable Long commentId) {

        User user = userService.getUserEntity();

        commentService.toggleCommentLike(commentId, user);

        return ApiResponse.success(null);
    }
}
