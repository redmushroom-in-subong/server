package com.rms.drifeserver.domain.user.dao.like;

import com.rms.drifeserver.domain.user.model.like.BoardLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardLikesRepository extends JpaRepository<BoardLikes,Long> {
}
