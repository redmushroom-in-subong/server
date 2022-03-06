package com.rms.drifeserver.domain.like.dao;

import com.rms.drifeserver.domain.like.model.StoreLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreLikesRepository extends JpaRepository<StoreLikes,Long> {
}
