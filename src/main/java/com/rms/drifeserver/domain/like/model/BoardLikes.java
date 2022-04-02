package com.rms.drifeserver.domain.like.model;

import com.rms.drifeserver.domain.board.model.Board;
import com.rms.drifeserver.domain.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@DiscriminatorValue(value = "board")
public class BoardLikes extends Like{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private BoardLikes(User user, Board board) {
        this.user = user;
        this.board = board;
    }

    public static BoardLikes of(User user, Board board) {
        return new BoardLikes(user, board);
    }
}
