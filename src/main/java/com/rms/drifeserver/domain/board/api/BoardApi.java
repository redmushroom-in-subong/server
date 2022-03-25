package com.rms.drifeserver.domain.board.api;

import com.rms.drifeserver.domain.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardApi {

    @GetMapping("/v1/boards")
    public ApiResponse<Object> getBoards() {
        return null;
    }

    @PostMapping("/v1/boards")
    public ApiResponse<Object> addBoard() {
        return null;
    }

    @GetMapping("/v1/boards/{boardId}")
    public ApiResponse<Object> getBoard(@PathVariable Long boardId) {
        return null;
    }

    @PutMapping("/v1/boards/{boardId}")
    public ApiResponse<Object> updateBoard(@PathVariable Long boardId) {
        return null;
    }

    @DeleteMapping("/v1/boards/{boardId}")
    public ApiResponse<Object> deleteBoard(@PathVariable Long boardId) {
        return null;
    }

    @PostMapping("/v1/boards/{boardId}/like")
    public ApiResponse<Object> likeBoard(@PathVariable Long boardId) {
        return null;
    }
}
