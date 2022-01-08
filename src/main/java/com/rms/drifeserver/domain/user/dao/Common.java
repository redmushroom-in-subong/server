package com.rms.drifeserver.domain.user.dao;

import com.rms.drifeserver.domain.user.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class Common {
    public RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setUserSeq(rs.getInt("USER_SEQ"));
            user.setUserId(rs.getString("USER_ID"));
            user.setNickname(rs.getString("NICKNAME"));
            user.setPassword(rs.getString("PASSWORD"));
            user.setPhone(rs.getString("PHONE"));
            user.setLocSi(rs.getString("LOC_SI"));
            user.setLocGu(rs.getString("LOC_GU"));
            user.setJoinDate(rs.getString("JOIN_DATE"));
            return user;
        };
    }
}
