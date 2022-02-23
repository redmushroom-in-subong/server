package com.rms.drifeserver.domain.user.model;

import com.rms.drifeserver.domain.board.model.Board;
import com.rms.drifeserver.domain.review.model.Review;
import com.rms.drifeserver.domain.review.model.Visit;
import com.rms.drifeserver.domain.user.model.Badge;
import com.rms.drifeserver.domain.user.model.like.BoardLikes;
import com.rms.drifeserver.domain.user.model.like.CommentLikes;
import com.rms.drifeserver.domain.user.model.like.ReviewLikes;
import com.rms.drifeserver.domain.user.model.like.StoreLikes;
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

    /**
     * 필요 없어 보이는 연관관계
     */
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private List<BoardLikes> boardLikes = new ArrayList();
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private List<CommentLikes> commentLikes = new ArrayList();
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private List<ReviewLikes> reviewLikes = new ArrayList();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<StoreLikes> storeLikes = new ArrayList();

}
