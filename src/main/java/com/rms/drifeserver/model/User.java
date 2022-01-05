package com.rms.drifeserver.model;
//ㅋㅋ
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
    public User(String userId, String password, String nickname, String phone) {
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
        this.phone = phone;
    }
    public User(){

    }

    public Integer getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(Integer userSeq) {
        this.userSeq = userSeq;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocSi() {
        return locSi;
    }

    public void setLocSi(String locSi) {
        this.locSi = locSi;
    }

    public String getLocGu() {
        return locGu;
    }

    public void setLocGu(String locGu) {
        this.locGu = locGu;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }
}
