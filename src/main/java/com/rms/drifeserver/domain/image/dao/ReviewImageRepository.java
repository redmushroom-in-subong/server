package com.rms.drifeserver.domain.image.dao;


import com.rms.drifeserver.domain.image.model.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {
}
