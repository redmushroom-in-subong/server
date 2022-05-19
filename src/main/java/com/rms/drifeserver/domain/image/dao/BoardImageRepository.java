package com.rms.drifeserver.domain.image.dao;

import com.rms.drifeserver.domain.image.model.BoardImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardImageRepository extends JpaRepository<BoardImage, Long> {
}
