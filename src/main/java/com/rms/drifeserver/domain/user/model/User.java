package com.rms.drifeserver.domain.user.model;

import com.rms.drifeserver.domain.badge.model.Badge;
import com.rms.drifeserver.domain.board.model.Board;
import com.rms.drifeserver.domain.review.model.Review;
import com.rms.drifeserver.domain.review.model.Visit;
import com.rms.drifeserver.domain.like.model.StoreLikes;
import com.rms.drifeserver.domain.oauth.entity.ProviderType;
import com.rms.drifeserver.domain.oauth.entity.RoleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
@ToString
public class User {
    @JsonIgnore
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", length = 64, unique = true)
    @NotNull
    @Size(max = 64)
    private String userId;

    @Column(name = "username", length = 100)
    @NotNull
    @Size(max = 100)
    private String username;

    @Column(name = "password", length = 100)
    @NotNull
    @Size(max = 100)
    private String password;

    @Column(name = "email", length = 512, unique = true)
    @NotNull
    @Size(max = 512)
    private String email;

    @Column(name = "email_verifiedYn", length = 1)
    @NotNull
    @Size(min = 1, max = 1)
    private String emailVerifiedYn;

    @Column(name = "profile_image_url", length = 512)
    @NotNull
    @Size(max = 512)
    private String profileImageUrl;

    @Column(name = "provider_type", length = 20)
    @Enumerated(EnumType.STRING)
    @NotNull
    private ProviderType providerType;

    @Column(name = "role_type", length = 20)
    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleType roleType;



    /**
     *
     */
    @OneToOne
    @JoinColumn(name="badge_id")
    private Badge myBadge;

    @Column(name = "x_pos")
    private int xPos;
    @Column(name = "y_pos")
    private int yPos;
    @Column(name = "phone")
    private String phone;
    @Column(name = "region_code")
    private int region_code;


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Review> myReviewList = new ArrayList();
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Visit> myVisitList = new ArrayList();
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Board> myBoardList = new ArrayList();
    //추후 알림,첨부타일

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<StoreLikes> storeLikes = new ArrayList();


    public User(
            @NotNull @Size(max = 64) String userId,
            @NotNull @Size(max = 100) String username,
            @NotNull @Size(max = 512) String email,
            @NotNull @Size(max = 1) String emailVerifiedYn,
            @NotNull @Size(max = 512) String profileImageUrl,
            @NotNull ProviderType providerType,
            @NotNull RoleType roleType,
            @NotNull LocalDateTime createdAt,
            @NotNull LocalDateTime modifiedAt
    ) {
        this.userId = userId;
        this.username = username;
        this.email = email != null ? email : "NO_EMAIL";
        this.emailVerifiedYn = emailVerifiedYn;
        this.profileImageUrl = profileImageUrl != null ? profileImageUrl : "";
        this.providerType = providerType;
        this.roleType = roleType;
    }
}
