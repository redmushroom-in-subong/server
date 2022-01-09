package com.rms.drifeserver.domain.board.repository;

import com.rms.drifeserver.domain.board.model.Board;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class JdbcBoardRepository implements BoardRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcBoardRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Board save(Board board) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("board").usingGeneratedKeyColumns("board_seq");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("writer", board.getWriter());
        parameters.put("title", board.getTitle());
        parameters.put("context", board.getContext());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        board.setBoardSeq(key.intValue());
        return board;
    }

    @Override
    public Optional<Board> findByTitle(String title) {
        List<Board> result = jdbcTemplate.query("select * from board where title = ?", boardRowMapper(), title);
        return result.stream().findAny();
    }

    private RowMapper<Board> boardRowMapper() {
        return (rs, rowNum) -> {
            Board board = new Board();
            board.setBoardSeq(rs.getInt("board_seq"));
            board.setWriter(rs.getString("writer"));
            board.setTitle(rs.getString("title"));
            board.setContext(rs.getString("context"));
            return board;
        };
    }

    @Override
    public List<Board> findAll() {
        return jdbcTemplate.query("select * from board", boardRowMapper());
    }
}
