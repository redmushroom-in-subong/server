package com.rms.drifeserver.domain.review.model;

import com.rms.drifeserver.domain.common.model.BaseTimeEntity;
import com.rms.drifeserver.domain.image.model.ReviewImage;
import com.rms.drifeserver.domain.store.model.Store;
import com.rms.drifeserver.domain.user.model.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ReviewKeyword> reviewKeywords = new ArrayList<>();

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ReviewImage> reviewImages = new ArrayList<>();

    private String contents;

    private Review(User user, Store store, String contents) {
        this.user = user;
        this.store = store;
        this.contents = contents;
    }

    public static Review of(User user, Store store, String contents, List<Long> reviewKeywordIds, List<String> imageUrls){
        Review review = new Review(user,store, contents);
        review.addReviewKeyword(reviewKeywordIds);
        review.addReviewImages(imageUrls);
        return review;
    }

    public void update(String contents, List<ReviewKeywordType> reviewKeywordTypes, List<String> imageUrls) {
        this.contents = contents;
        this.reviewKeywords.clear();
        updateReviewKeyword(reviewKeywordTypes);
        this.reviewImages.clear();
        addReviewImages(imageUrls);
    }

    public void addReviewKeyword(List<Long> reviewKeywordIds) {
        reviewKeywordIds.forEach(id -> this.reviewKeywords.add(ReviewKeyword.of(this, ReviewKeywordType.of(id))));
    }

    public void updateReviewKeyword(List<ReviewKeywordType> reviewKeywordTypes) {
        reviewKeywordTypes.forEach(type -> this.reviewKeywords.add(ReviewKeyword.of(this, type)));
    }

    public void addReviewImages(List<String> imageUrls) {
        imageUrls.forEach(imageUrl -> this.reviewImages.add(ReviewImage.of(this.user, this, imageUrl)));
    }
}
