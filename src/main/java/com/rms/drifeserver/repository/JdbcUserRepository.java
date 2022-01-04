package com.rms.drifeserver.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rms.drifeserver.model.User;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
@Primary
@Component
public class JdbcUserRepository implements UserRepository {
    //USE JDBC
    /**### named parameter jdbctemplate vs jdbctemplate###
     *
     *
     *
     * https://stackoverflow.com/questions/16359316/namedparameterjdbctemplate-vs-jdbctemplat
     *
     *
     *
     */
    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    //@Autowired 생략 - 1 constructor
    public JdbcUserRepository(NamedParameterJdbcTemplate nPJdbcTemplate) {
        this.namedJdbcTemplate=nPJdbcTemplate;
    }
    //--end
    private RowMapper<User> userRowMapper() {
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
    @Override public List<User> getAllUsers(){
        String sql="select * from USER limit 100";
        List<User> ret=namedJdbcTemplate.query(sql,userRowMapper());
        return ret;
    }
    @Override public Optional<User> findUserByUserId(String userId){
        String sql="select * from USER where user_id= :userId";
        Map<String,Object> argMap=new HashMap<String,Object>();
        argMap.put("userId", userId);
        List<User> ret = namedJdbcTemplate.query(sql,argMap,userRowMapper());
        return ret.stream().findAny();
    }
    @Override public Optional<User> findUserByUserSeq(Integer userSeq){
        return null;
    }
    @Override public Integer createUser(User user){
        String sql="insert into USER (user_id,password,nickname,phone,loc_si,loc_gu)" +
                              "values(:userId,:password,:nickname,:phone,:locSi,:locGu)";
        // User 객체를 <필드명,값> 형태의 맵으로 바꿔줌
        ObjectMapper mapObject = new ObjectMapper();
        Map < String, Object > mapObj = mapObject.convertValue(user, Map.class);
        //end
        Integer ret=namedJdbcTemplate.update(sql,mapObj);
        return ret;
    }
    @Override public void updateUser(User user){}
    @Override public void deleteUser(User user){}
}
