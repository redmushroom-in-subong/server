package com.rms.drifeserver.domain.board.service.dto.request;

import com.rms.drifeserver.domain.board.model.Board;
import com.rms.drifeserver.domain.user.model.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddBoardRequest {

    private String title;

    private String contents;

    private List<String> imageUrls;

    public Board toBoard(User user) {
        return Board.of(user, this.title, this.contents, this.imageUrls);
    }
}
