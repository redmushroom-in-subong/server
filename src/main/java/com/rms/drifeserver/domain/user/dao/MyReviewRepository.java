package com.rms.drifeserver.domain.user.dao;

import com.rms.drifeserver.domain.review.model.Review;
import com.rms.drifeserver.domain.user.model.User;
import com.rms.drifeserver.domain.user.service.dto.response.MyReviewResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyReviewRepository extends JpaRepository<Review, Long> {
    @Query(value="select new com.rms.drifeserver.domain.user.service.dto.response.MyReviewResponse(" +
            "r.id,r.store.id,r.store.storeName,'http://google.com',r.contents" +
            ") from Review r where r.user.id=:userId")
    List<MyReviewResponse> findMyReviews(Long userId);
}
