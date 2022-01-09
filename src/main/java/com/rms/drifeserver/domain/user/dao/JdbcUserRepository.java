package com.rms.drifeserver.domain.user.dao;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.rms.drifeserver.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.*;
//기본적인 쿼리 (select,delete,updata,insert + 조건절,join없는)를 실행하는 method만 선언
//이외는 DAO 따로 만들어서 사용 - 블로그참고
@Primary
@Component
@RequiredArgsConstructor
public class JdbcUserRepository implements UserRepository {
    //USE JDBC
    /**### named parameter jdbctemplate vs jdbctemplate###
     */
    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    private final Common comm;
    @Override public List<User> getAllUsers(){
        String sql="select * from USER limit 100";
        List<User> ret=namedJdbcTemplate.query(sql,comm.userRowMapper());
        return ret;
    }
    @Override public Integer createUser(User user){
        String sql="insert into USER (user_id,password,nickname,phone,loc_si,loc_gu)" +
                "values(:userId,:password,:nickname,:phone,:locSi,:locGu)";
        // User 객체를 <필드명,값> 형태의 맵으로 바꿔줌
        ObjectMapper mapObject = new ObjectMapper();
        Map < String, Object > mapObj = mapObject.convertValue(user, Map.class);
        //end
        System.out.println("mapObj.toString() = " + mapObj.toString());
        Integer ret=namedJdbcTemplate.update(sql,mapObj);
        return ret;
    }
    @Override public Integer updateUser(User user,String targetId){
        String sql="update USER set " +
                "user_id=:userId," +
                "password=:password," +
                "nickname=:nickname," +
                "phone=:phone," +
                "loc_si=:locSi," +
                "loc_gu=:locGu " +
                "where user_id=:targetId";
        // User 객체를 <필드명,값> 형태의 맵으로 바꿔줌
        ObjectMapper mapObject = new ObjectMapper();
        Map < String, Object > mapObj = mapObject.convertValue(user, Map.class);
        mapObj.put("targetId",targetId);
        //end
        System.out.println("mapObj.toString() = " + mapObj.toString());
        Integer ret=namedJdbcTemplate.update(sql,mapObj);
        return ret;
    }
    @Override public Integer deleteUser(String targetId){
        String sql="delete from USER where user_id=:id";
        Map<String, Object> mapObj = new HashMap<>();
        mapObj.put("id",targetId);
        Integer ret=namedJdbcTemplate.update(sql,mapObj);
        return ret;
    }
}