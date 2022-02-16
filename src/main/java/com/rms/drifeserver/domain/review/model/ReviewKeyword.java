package com.rms.drifeserver.domain.review.model;

import javax.persistence.*;

@Entity
public class ReviewKeyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_type_id", nullable = false)
    private ReviewKeywordType reviewKeywordType;
}
