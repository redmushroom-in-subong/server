package com.rms.drifeserver.domain.like.model;

import com.rms.drifeserver.domain.review.model.Review;
import com.rms.drifeserver.domain.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor
@DiscriminatorValue(value = "review")
public class ReviewLikes extends Like {
    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    private ReviewLikes(User user, Review review) {
        this.user = user;
        this.review = review;
    }

    public static ReviewLikes of(User user, Review review) {
        return new ReviewLikes(user,review);
    }
}
