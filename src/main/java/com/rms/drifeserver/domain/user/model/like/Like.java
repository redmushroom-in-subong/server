package com.rms.drifeserver.domain.user.model.like;

import com.rms.drifeserver.domain.common.model.TimeStamp;
import com.rms.drifeserver.domain.user.model.User;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
@Entity
@Table(name = "likes")
public class Like extends TimeStamp {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
