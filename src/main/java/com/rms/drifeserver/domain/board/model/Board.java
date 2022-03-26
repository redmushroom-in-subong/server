package com.rms.drifeserver.domain.board.model;

import com.rms.drifeserver.domain.comment.model.Comment;
import com.rms.drifeserver.domain.common.model.BaseTimeEntity;
import com.rms.drifeserver.domain.user.model.User;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Comment> comments = new ArrayList<>();

    @Column(length = 10)
    private String code;

    private String title;

    private String contents;

    private Long viewCnt;
}
