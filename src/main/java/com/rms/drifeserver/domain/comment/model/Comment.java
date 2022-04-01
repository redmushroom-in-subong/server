package com.rms.drifeserver.domain.comment.model;

import com.rms.drifeserver.domain.board.model.Board;
import com.rms.drifeserver.domain.common.model.BaseTimeEntity;
import com.rms.drifeserver.domain.user.model.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String contents;

    private Comment(Board board, User user, String contents) {
        this.board = board;
        this.user = user;
        this.contents = contents;
    }

    public static Comment of(Board board, User user, String contents) {
        return new Comment(board, user, contents);
    }

    public void update(String contents) {
        this.contents = contents;
    }
}
