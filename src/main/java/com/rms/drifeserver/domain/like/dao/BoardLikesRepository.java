package com.rms.drifeserver.domain.like.dao;

import com.rms.drifeserver.domain.board.model.Board;
import com.rms.drifeserver.domain.like.model.BoardLikes;
import com.rms.drifeserver.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardLikesRepository extends JpaRepository<BoardLikes, Long> {

    Long countByBoard(Board board);

    BoardLikes findByUserAndBoard(User user, Board board);
}
