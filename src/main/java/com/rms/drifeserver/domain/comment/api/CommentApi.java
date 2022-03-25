package com.rms.drifeserver.domain.comment.api;

import com.rms.drifeserver.domain.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentApi {

    @PostMapping("/v1/boards/{boardId}/comments")
    public ApiResponse<Object> addComment(@PathVariable Long boardId) {
        return null;
    }

    @PutMapping("/v1/boards/{boardId}/comments/{{commentId}}")
    public ApiResponse<Object> updateComment(@PathVariable Long boardId, @PathVariable Long commentId) {
        return null;
    }

    @DeleteMapping("/v1/boards/{boardId}/comments/{{commentId}}")
    public ApiResponse<Object> deleteComment(@PathVariable Long boardId, @PathVariable Long commentId) {
        return null;
    }

    @PostMapping("/v1/boards/{boardId}/comments/{{commentId}}/like")
    public ApiResponse<Object> likeComment(@PathVariable Long boardId, @PathVariable Long commentId) {
        return null;
    }
}
