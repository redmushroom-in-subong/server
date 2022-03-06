package com.rms.drifeserver.domain.like.model;

import com.rms.drifeserver.domain.board.model.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@DiscriminatorValue(value = "board")
public class BoardLikes extends Like{
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
}
