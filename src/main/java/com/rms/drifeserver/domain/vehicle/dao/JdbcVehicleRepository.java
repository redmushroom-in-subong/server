package com.rms.drifeserver.domain.vehicle.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rms.drifeserver.domain.vehicle.model.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Primary
@Repository
@RequiredArgsConstructor
public class JdbcVehicleRepository implements VehicleRepository{

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer save(Vehicle vehicle) {
        String sql="insert into VEHICLE (user_seq,vehicle_num)" +
                "values(:userSeq,:vehicleNum)";
        // User 객체를 <필드명,값> 형태의 맵으로 바꿔줌
        ObjectMapper mapObject = new ObjectMapper();
        Map < String, Object > mapObj = mapObject.convertValue(vehicle, Map.class);
        //end
        Integer ret=namedParameterJdbcTemplate.update(sql,mapObj);
        return ret;
    }

    @Override
    public List<Vehicle> findAll() {
        String sql = "select * from VEHICLE";
        List<Vehicle> ret = namedParameterJdbcTemplate.query(sql, vehicleRowMapper());
        return ret;
    }

    @Override
    public Optional<Vehicle> findByNum(String vehicleNum) {
        String sql = "select * from VEHICLE where vehicle_num= :vehicle_num";
        Map<String, String> params = Collections.singletonMap("vehicle_num", vehicleNum);
        List<Vehicle> ret = namedParameterJdbcTemplate.query(sql, params, vehicleRowMapper());
        return ret.stream().findAny();
    }

    @Override
    public List<Vehicle> findByUser(Integer userSeq) {
        String sql = "select * from VEHICLE where user_seq= :user_seq";
        Map<String, Integer> params = Collections.singletonMap("user_seq", userSeq);
        List<Vehicle> ret = namedParameterJdbcTemplate.query(sql, params, vehicleRowMapper());
        return ret;
    }

    @Override
    public Integer deleteVehicle(Vehicle vehicle) {
        String sql="delete from USER where vehicle_num= :vehicle_num";
        Map<String, Object> mapObj = Collections.singletonMap("vehicle_num",vehicle.getVehicleNum());
        Integer ret=namedParameterJdbcTemplate.update(sql,mapObj);
        return ret;
    }

    private RowMapper<Vehicle> vehicleRowMapper() {
        return (rs, rowNum) -> {
            Vehicle vehicle = new Vehicle();
            vehicle.setUserSeq(rs.getInt("USER_SEQ"));
            vehicle.setVehicleNum(rs.getString("VEHICLE_NUM"));
            return vehicle;
        };
    }
}
