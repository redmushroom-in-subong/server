package com.rms.drifeserver.domain.badge.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class BadgeEarnCondition {
    @Column(name="visit_cnt")
    private int visitCnt;
    @Column(name="review_cnt")
    private int review_cnt;
}
