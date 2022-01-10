package com.rms.drifeserver.domain.board.repository;

import com.rms.drifeserver.domain.board.model.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    Integer save(Board board);
    Optional<Board> findByTitle(String title);
    List<Board> findAll();
}