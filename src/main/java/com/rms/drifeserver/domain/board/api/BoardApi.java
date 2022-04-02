package com.rms.drifeserver.domain.board.api;

import com.rms.drifeserver.domain.board.service.BoardService;
import com.rms.drifeserver.domain.board.service.dto.request.AddBoardRequest;
import com.rms.drifeserver.domain.board.service.dto.request.UpdateBoardRequest;
import com.rms.drifeserver.domain.board.service.dto.response.BoardDetailResponse;
import com.rms.drifeserver.domain.board.service.dto.response.BoardsResponse;
import com.rms.drifeserver.domain.common.dto.ApiResponse;
import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardApi {

    private final BoardService boardService;
    private final UserService userService;

    @GetMapping("/v1/boards")
    public ApiResponse<BoardsResponse> getBoards() {
        return null;
    }

    @GetMapping("/v1/boards/hot")
    public ApiResponse<BoardsResponse> getHotBoards() {
        return null;
    }

    @PostMapping("/v1/boards")
    public ApiResponse<Object> addBoard(@RequestBody AddBoardRequest request) {

        User user = userService.getUserEntity();

        boardService.addBoard(request, user);

        return ApiResponse.success(null);
    }

    @GetMapping("/v1/boards/{boardId}")
    public ApiResponse<BoardDetailResponse> getBoard(@PathVariable Long boardId) {
        return ApiResponse.success(boardService.getBoardDetail(boardId));
    }

    @PutMapping("/v1/boards/{boardId}")
    public ApiResponse<BoardDetailResponse> updateBoard(@RequestBody UpdateBoardRequest request, @PathVariable Long boardId) {

        User user = userService.getUserEntity();

        return ApiResponse.success(boardService.updateBoard(request, boardId, user));
    }

    @DeleteMapping("/v1/boards/{boardId}")
    public ApiResponse<Object> deleteBoard(@PathVariable Long boardId) {

        User user = userService.getUserEntity();

        boardService.deleteBoard(boardId, user);

        return ApiResponse.success(null);
    }

    @PostMapping("/v1/boards/{boardId}/like")
    public ApiResponse<Object> likeBoard(@PathVariable Long boardId) {

        User user = userService.getUserEntity();

        boardService.toggleBoardLike(boardId, user);

        return ApiResponse.success(null);
    }
}
