package com.rms.drifeserver.domain.user.model;

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
@Table(name = "bedge")
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
