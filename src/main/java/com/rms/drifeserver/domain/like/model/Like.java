package com.rms.drifeserver.domain.like.model;

import com.rms.drifeserver.domain.common.model.TimeStamp;
import com.rms.drifeserver.domain.user.model.User;
import lombok.Setter;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
@Entity
@Setter
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
