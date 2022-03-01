package com.rms.drifeserver.domain.review.model;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class ReviewKeywordType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @OneToMany(mappedBy = "reviewKeywordType", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ReviewKeyword> keywordReviews = new ArrayList<>();
}
