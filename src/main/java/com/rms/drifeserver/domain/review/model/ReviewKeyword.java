package com.rms.drifeserver.domain.review.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
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

    private ReviewKeyword(Review review, ReviewKeywordType reviewKeywordType) {
        this.review = review;
        this.reviewKeywordType = reviewKeywordType;
    }

    public static ReviewKeyword of(Review review, ReviewKeywordType reviewKeywordType) {
        return  new ReviewKeyword(review, reviewKeywordType);
    }
}
