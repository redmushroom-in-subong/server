package com.rms.drifeserver.domain.board.model;

import com.rms.drifeserver.domain.comment.model.Comment;
import com.rms.drifeserver.domain.common.model.BaseTimeEntity;
import com.rms.drifeserver.domain.image.model.BoardImage;
import com.rms.drifeserver.domain.user.model.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Comment> comments = new ArrayList<>();

    @Column(length = 10)
    private String code;

    private String title;

    private String contents;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<BoardImage> boardImages = new ArrayList<>();

    private Board(User user, String code, String title, String contents) {
        this.user = user;
        this.code = code;
        this.title = title;
        this.contents = contents;
    }

    public static Board of(User user, String title, String contents, List<String> imageUrls) {
        Board board = new Board(user, user.getRegionCode(), title, contents);
        board.addBoardImages(imageUrls);

        return board;
    }

    public void update(String title, String contents, List<String> imageUrls) {
        this.title = title;
        this.contents = contents;
        this.boardImages.clear();
        addBoardImages(imageUrls);
    }

    public void addBoardImages(List<String> imageUrls) {
        imageUrls.forEach(imageUrl -> this.boardImages.add(BoardImage.of(this.user, this, imageUrl)));
    }
}
