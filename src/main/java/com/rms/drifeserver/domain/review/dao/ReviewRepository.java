package com.rms.drifeserver.domain.review.dao;

import com.rms.drifeserver.domain.review.model.Review;
import com.rms.drifeserver.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByIdAndUserId(Long id, Long userId);

    List<Review> findAllByStoreId(Long storeId);

    List<Review> findAllByStoreIdAndUserId(Long storeId, Long userId);

    @Query("select r from Review r left join ReviewKeyword rk on ( r.id = rk.review.id ) where ( r.store.id = :storeId and rk.reviewKeywordType.id = :keywordTypeId )")
    List<Review> findAllByStoreIdAndKeywordTypeId(@Param("storeId") Long storeId, @Param("keywordTypeId") Long keywordTypeId);
}
