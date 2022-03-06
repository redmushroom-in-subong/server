package com.rms.drifeserver.domain.like.dao;

import com.rms.drifeserver.domain.like.model.BoardLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardLikesRepository extends JpaRepository<BoardLikes,Long> {
}
