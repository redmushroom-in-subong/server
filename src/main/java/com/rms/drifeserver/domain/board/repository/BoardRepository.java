package com.rms.drifeserver.domain.board.repository;

import com.rms.drifeserver.domain.board.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}