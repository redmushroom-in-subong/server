package com.rms.drifeserver.domain.board.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.sql.Timestamp;

@Getter
@Setter
public class Board {
    private int boardSeq;
    private int userSeq;
    private String title;
    private String context;
    //private String writer;
    private Timestamp writeAt;

    public Board(int userSeq, String title, String context){
        this.userSeq = userSeq;
        this.title = title;
        this.context = context;
        //this.writer = writer;
    }

    public Board(){}

    public void setBoardSeq(int boardSeq) { this.boardSeq = boardSeq; }

    public int getUserSeq() { return userSeq; }

    public int getBoardSeq() { return boardSeq; }

    public String getTitle() { return title; }

    public Timestamp getWriteAt() { return writeAt; }

    //public String getWriter() { return writer; }

    public String getContext() { return context; }

    public void setUserSeq(int userSeq) { this.userSeq = userSeq; }

    public void setTitle(String title) { this.title = title; }

    public void setContext(String context) { this.context = context; }

    public void setWriteAt(Timestamp writeAt) { this.writeAt = writeAt; }

    // public void setWriter(String writer) { this.writer = writer; }
}
