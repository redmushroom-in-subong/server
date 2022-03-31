package com.rms.drifeserver.domain.image.model;

import com.rms.drifeserver.domain.review.model.Review;
import com.rms.drifeserver.domain.user.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@DiscriminatorValue(value = "review")
public class ReviewImage extends Image{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    private ReviewImage(User user, Review review, String imageUrl) {
        this.user = user;
        this.review = review;
        this.imageUrl = imageUrl;
    }

    public static ReviewImage of(User user, Review review, String imagUrl) {
        return new ReviewImage(user, review, imagUrl);
    }
}
