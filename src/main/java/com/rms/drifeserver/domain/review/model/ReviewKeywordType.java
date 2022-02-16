package com.rms.drifeserver.domain.review.model;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
public class ReviewKeywordType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;
}
