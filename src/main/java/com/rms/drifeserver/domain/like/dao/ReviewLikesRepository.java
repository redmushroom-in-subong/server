package com.rms.drifeserver.domain.like.dao;

import com.rms.drifeserver.domain.like.model.ReviewLikes;
import com.rms.drifeserver.domain.review.model.Review;
import com.rms.drifeserver.domain.store.model.Store;
import com.rms.drifeserver.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewLikesRepository extends JpaRepository<ReviewLikes,Long> {

    ReviewLikes findByUserAndReview(User user, Review review);

    @Query("select count(rl) from ReviewLikes rl where rl.review = :review  and rl.review.store = :store")
    Long countByStoreAndReview(@Param("store") Store store, @Param("review") Review review);
}
