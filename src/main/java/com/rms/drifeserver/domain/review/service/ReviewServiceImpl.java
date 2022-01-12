package com.rms.drifeserver.domain.review.service;

import com.rms.drifeserver.domain.review.dao.ReviewRepository;
import com.rms.drifeserver.domain.review.model.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    @Override
    public Optional<Review> saveReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Optional<Review> findBySeq(Integer reviewSeq) {
        return reviewRepository.findBySeq(reviewSeq);
    }

    @Override
    public List<Review> findByVehicle(Integer vehicleSeq) {
        return reviewRepository.findByVehicle(vehicleSeq);
    }

    @Override
    public List<Review> findByWriter(Integer writerSeq) {
        return reviewRepository.findByWriter(writerSeq);
    }

    @Override
    public void deleteReview(Review review) {
        reviewRepository.delete(review);
    }
}
