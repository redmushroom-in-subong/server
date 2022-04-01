package com.rms.drifeserver.domain.board.service.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddBoardRequest {

    private String title;

    private String contents;

//    public toBoard(User user) {
//        return Board.of();
//    }
}
