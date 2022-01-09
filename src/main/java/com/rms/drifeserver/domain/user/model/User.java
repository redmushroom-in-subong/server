package com.rms.drifeserver.domain.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Integer userSeq;
    private String userId;
    private String password;
    private String nickname;
    private String phone;
    private String locSi;
    private String locGu;
    private String joinDate;
    public User(String userId, String password, String nickname, String phone, String locSi, String locGu) {
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
        this.phone = phone;
        this.locSi = locSi;
        this.locGu = locGu;
    }
    public User() {

    }

}
