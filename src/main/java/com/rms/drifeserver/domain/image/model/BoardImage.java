package com.rms.drifeserver.domain.image.model;

import com.rms.drifeserver.domain.board.model.Board;
import com.rms.drifeserver.domain.review.model.Review;
import com.rms.drifeserver.domain.user.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@DiscriminatorValue(value = "board")
public class BoardImage extends Image {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private BoardImage(User user, Board board, String imageUrl) {
        this.user = user;
        this.board = board;
        this.imageUrl = imageUrl;
    }

    public static BoardImage of(User user, Board board, String imagUrl) {
        return new BoardImage(user, board, imagUrl);
    }
}
