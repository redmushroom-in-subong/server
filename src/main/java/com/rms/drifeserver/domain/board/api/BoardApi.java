package com.rms.drifeserver.domain.board.api;


import com.rms.drifeserver.domain.board.model.Board;
import com.rms.drifeserver.domain.board.repository.BoardRepository;
import com.rms.drifeserver.domain.board.repository.JdbcBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardApi {

    private final BoardRepository boardRepository;

    public BoardApi(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Autowired
    JdbcBoardRepository jdbcBoardRepository;

    @GetMapping("")
    @ResponseBody
    public List<Board> getAll(){
        return jdbcBoardRepository.findAll();
    }


/*
    @GetMapping("/boards")
    public String list(Model model) {
        List<Board> posts =  boardRepository.findAll();
        model.addAttribute("posts", posts);
        return "boards/boards";
    }

    @GetMapping("/boards/write")
    public String boardInsert(Model model) {
        return "boards/boardWrite";
    }

    @PostMapping("/boards/write")
    public String create(BoardForm form) {
        Board board = new Board();
        board.setWriter(form.getWriter());
        board.setTitle(form.getTitle());
        board.setContext(form.getContext());

        boardRepository.save(board);

        return "redirect:/boards";
    }
 */
}
