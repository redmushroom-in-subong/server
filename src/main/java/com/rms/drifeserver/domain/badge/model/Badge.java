package com.rms.drifeserver.domain.badge.model;

import com.rms.drifeserver.domain.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "badge")
public class Badge {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "badge_code")
    private BadgeCode badgeCode;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


}
