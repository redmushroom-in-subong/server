package com.rms.drifeserver.domain.like.model;

import com.rms.drifeserver.domain.comment.model.Comment;
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
@DiscriminatorValue(value = "comment")
public class CommentLikes extends Like {
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment Comment;
}
