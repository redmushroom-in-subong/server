package com.rms.drifeserver.domain.board.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Board {
    private int boardSeq;
    private String title;
    private String context;
    //private String writer;
    private LocalDateTime writeAt;

    public Board(String title, String context){
        this.title = title;
        this.context = context;
        //this.writer = writer;
    }

    public Board(){}

    public void setBoardSeq(int boardSeq) { this.boardSeq = boardSeq; }

    public int getBoardSeq() { return boardSeq; }

    public String getTitle() { return title; }

    //public String getWriter() { return writer; }

    public String getContext() { return context; }

    public void setTitle(String title) { this.title = title; }

    public void setContext(String context) { this.context = context; }

   // public void setWriter(String writer) { this.writer = writer; }
}
