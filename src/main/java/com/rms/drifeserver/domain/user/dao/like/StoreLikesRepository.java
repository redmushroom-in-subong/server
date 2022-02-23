package com.rms.drifeserver.domain.user.dao.like;

import com.rms.drifeserver.domain.user.model.like.StoreLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreLikesRepository extends JpaRepository<StoreLikes,Long> {
}
