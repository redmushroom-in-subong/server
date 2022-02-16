package com.rms.drifeserver.domain.user.model.entity;

import com.rms.drifeserver.domain.board.model.Board;
import com.rms.drifeserver.domain.review.model.Review;
import com.rms.drifeserver.domain.review.model.Visit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_nickname")
    private String nickname;
    @Column(name = "x_pos")
    private int xPos;
    @Column(name = "y_pos")
    private int yPos;
    @Column(name = "phone")
    private String phone;
    @Column(name = "profile_url")
    private String profileUrl;
    @Column(name = "region_code")
    private int region_code;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Review> myReviewList = new ArrayList();
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Visit> myVisitList = new ArrayList();
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Badge> myBadgeList = new ArrayList();
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Board> myBoardList = new ArrayList();
    //추후 알림,첨부타일
}
