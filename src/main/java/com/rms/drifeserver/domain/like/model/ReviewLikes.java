package com.rms.drifeserver.domain.like.model;

import com.rms.drifeserver.domain.review.model.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@DiscriminatorValue(value = "review")
public class ReviewLikes extends Like {
    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;
}
