package com.rms.drifeserver.domain.board.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rms.drifeserver.domain.board.model.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class JdbcBoardRepository implements BoardRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer savePost(Board board) {
        String sql="insert into BOARD (user_seq,title,context)" +
                "values(:userSeq,:title,:context)";

        ObjectMapper mapObject = new ObjectMapper();
        Map<String, Object> mapObj = mapObject.convertValue(board, Map.class);

        Integer ret=namedParameterJdbcTemplate.update(sql,mapObj);
        return ret;
    }

    @Override
    public Integer updatePost(Board board, int thisBoardSeq) {
        String sql="update BOARD set " +
                "title=:title," +
                "context=:context " +
                "where board_seq=:thisBoardSeq";

        ObjectMapper mapObject = new ObjectMapper();
        Map <String, Object> mapObj = mapObject.convertValue(board, Map.class);
        mapObj.put("thisBoardSeq",thisBoardSeq);

        Integer ret=namedParameterJdbcTemplate.update(sql,mapObj);
        return ret;
    }


    @Override
    public Optional<Board> findByTitle(String title) {
        String sql = "select * from BOARD where title= :title";
        Map<String, String> params = Collections.singletonMap("title", title);
        List<Board> result = namedParameterJdbcTemplate.query(sql, params, boardRowMapper());
        return result.stream().findAny();
    }

    @Override
    public Optional<Board> findByBoardSeq(Integer boardSeq) {
        String sql = "select * from BOARD where board_seq= :boardSeq";
        Map<String, Integer> params = Collections.singletonMap("boardSeq", boardSeq);
        List<Board> result = namedParameterJdbcTemplate.query(sql, params, boardRowMapper());
        return Optional.empty();
    }

    @Override
    public List<Board> findAll() {
        return namedParameterJdbcTemplate.query("select * from BOARD", boardRowMapper());
    }


    private RowMapper<Board> boardRowMapper() {
        return (rs, rowNum) -> {
            Board board = new Board();
            board.setBoardSeq(rs.getInt("board_seq"));
            board.setUserSeq(rs.getInt("user_seq"));
           // board.setWriter(rs.getString("writer"));
            board.setTitle(rs.getString("title"));
            board.setContext(rs.getString("context"));
            board.setWriteAt(rs.getTimestamp("write_at"));
            return board;
        };
    }

}
