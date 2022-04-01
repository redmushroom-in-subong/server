package com.rms.drifeserver.domain.review.dao;

import com.rms.drifeserver.domain.review.model.ReviewKeyword;
import com.rms.drifeserver.domain.review.service.dto.response.ReviewKeywordCountResponse;
import com.rms.drifeserver.domain.store.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewKeywordRepository extends JpaRepository<ReviewKeyword, Long> {

    @Query("select new com.rms.drifeserver.domain.review.service.dto.response.ReviewKeywordCountResponse(" +
            " rk.reviewKeywordType.id, " +
            " rk.reviewKeywordType.name, " +
            " count(rk)) " +
            " from ReviewKeyword rk where rk.review.store = :store group by rk.reviewKeywordType order by count(rk) desc")
    List<ReviewKeywordCountResponse> getReviewKeywordCountByStore(@Param("store") Store store);
}
