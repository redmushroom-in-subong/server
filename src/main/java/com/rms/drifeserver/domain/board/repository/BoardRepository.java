package com.rms.drifeserver.domain.board.repository;

import com.rms.drifeserver.domain.board.model.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    Integer savePost(Board board);
    Integer updatePost(Board board, int boardSeq);
    Optional<Board> findByTitle(String title);
    Optional<Board> findByBoardSeq(Integer boardSeq);
    List<Board> findAll();
}