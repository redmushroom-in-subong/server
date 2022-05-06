package com.rms.drifeserver.domain.board.dao;

import com.rms.drifeserver.domain.board.model.Board;
import com.rms.drifeserver.domain.user.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Optional<Board> findByIdAndUser(Long boardId, User user);

    List<Board> findByCodeOrderByCreatedAtDesc(String code, Pageable pageable);

    @Query("select b from BoardLikes bl join Board b on (b.code = :code) group by b having count(b) >=10")
    List<Board> findHotBoards(@Param("code") String code, Pageable pageable);

    @Query("select b from Board b where b.code = :code and (b.title like %:keyword% or b.contents like %:keyword%) " +
            "order by b.createdAt desc")
    List<Board> findBoardsByKeyword(@Param("code") String code, @Param("keyword") String keyword,
                                    Pageable pageable);
}