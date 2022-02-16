package com.rms.drifeserver.domain.user.model.entity;

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
@Table(name = "bedge")
public class Bedge {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "bedge_code")
    private BedgeCode bedgeCode;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;



}
