package com.rms.drifeserver.domain.review.service;

import com.rms.drifeserver.domain.review.model.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    Optional<Review> saveReview(Review review);
    List<Review> findAll();
    Optional<Review> findBySeq(Integer reviewSeq);
    List<Review> findByVehicle(Integer vehicleSeq);
    List<Review> findByWriter(Integer writerSeq);
    void deleteReview(Review review);
}
