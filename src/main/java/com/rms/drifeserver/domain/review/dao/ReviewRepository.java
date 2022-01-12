package com.rms.drifeserver.domain.review.dao;

import com.rms.drifeserver.domain.review.model.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
    Optional<Review> save(Review review);
    List<Review> findAll();
    Optional<Review> findBySeq(Integer reviewSeq);
    List<Review> findByVehicle(Integer vehicleSeq);
    List<Review> findByWriter(Integer writerSeq);
    void delete(Review review);
}
