package com.rms.drifeserver.domain.image.dao;


import com.rms.drifeserver.domain.image.model.ReviewImage;
import com.rms.drifeserver.domain.store.service.dto.response.StoreImageResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {
    @Query("select i from ReviewImage i where i.review.store.id = :storeId")
    List<StoreImageResponse> findStoreImages(@Param("storeId") Long storeId);
}
