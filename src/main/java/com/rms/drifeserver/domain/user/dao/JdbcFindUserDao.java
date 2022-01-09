package com.rms.drifeserver.domain.user.dao;

import com.rms.drifeserver.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JdbcFindUserDao implements FindUserDao {
    //USE JDBC
    /**### named parameter jdbctemplate vs jdbctemplate###
     */
    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    private final Common comm;

    @Override public Optional<User> findUserByUserId(String userId){
        String sql="select * from USER where user_id= :userId";
        Map<String,Object> argMap=new HashMap<String,Object>();
        argMap.put("userId", userId);
        List<User> ret = namedJdbcTemplate.query(sql,argMap,comm.userRowMapper());
        return ret.stream().findAny();
    }
    @Override public Optional<User> findUserByUserSeq(Integer userSeq){
        return null;
    }

}
