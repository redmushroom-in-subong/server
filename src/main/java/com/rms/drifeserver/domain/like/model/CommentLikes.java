package com.rms.drifeserver.domain.like.model;

import com.rms.drifeserver.domain.comment.model.Comment;
import com.rms.drifeserver.domain.review.model.Review;
import com.rms.drifeserver.domain.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@DiscriminatorValue(value = "comment")
public class CommentLikes extends Like {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    private CommentLikes(User user, Comment comment) {
        this.user = user;
        this.comment = comment;
    }

    public static CommentLikes of(User user, Comment comment) {
        return new CommentLikes(user,comment);
    }
}
