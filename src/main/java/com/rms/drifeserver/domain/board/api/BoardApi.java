package com.rms.drifeserver.domain.board.api;


import com.rms.drifeserver.domain.board.model.Board;
import com.rms.drifeserver.domain.board.repository.BoardRepository;
import com.rms.drifeserver.domain.board.repository.JdbcBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardApi {

    private final BoardRepository boardRepository;

    @GetMapping("")
    @ResponseBody
    public Map<String,Object> getAll(){
        Map<String,Object> ret=new HashMap<String,Object>();
        List<Board> allPosts = boardRepository.findAll();
        ret.put("result",allPosts);
        return ret;
    }

    @PostMapping("/write")
    @ResponseBody
    public Map<String,Object> write(@RequestBody Board board) {
        Map<String,Object> ret = new HashMap<String,Object>();
        try{
            boardRepository.savePost(board);
            Optional<Board> byTitle = boardRepository.findByTitle(board.getTitle());
            ret.put("result",byTitle.get());
        }catch (Exception e){
            ret.put("state","error");
            ret.put("message",e.getMessage());
        }finally {
            return ret;
        }
    }

    @PutMapping("/{boardSeq}")
    @ResponseBody
    public Map<String,Object> updatePost(@PathVariable("boardSeq") int boardSeq,@RequestBody Board board) {
        Map<String, Object> ret = new HashMap<String, Object>();

        try {
            Integer isExist = boardRepository.updatePost(board, boardSeq);
            if(isExist == 0){
                ret.put("state","fail");
                ret.put("result","no post (board_seq:" + boardSeq + ")");
            }
            else{
                Optional<Board> byBoardSeq = boardRepository.findByBoardSeq(boardSeq);
                ret.put("result", byBoardSeq);
            }
        } catch (Exception e){
            ret.put("state","error");
            ret.put("result",e.getMessage());
        } finally {
            return ret;
        }
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
