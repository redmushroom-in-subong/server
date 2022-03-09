package com.rms.drifeserver.domain.badge.model;

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
@Table(name = "badge_code")
public class BadgeCode {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "badge_name")
    private String badgeName;
    @Column(name="review_cnt")
    private int review_cnt;
    @OneToMany(mappedBy = "badgeCode")
    private List<Badge> badges=new ArrayList<>();
}
