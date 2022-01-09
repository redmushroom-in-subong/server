package com.rms.drifeserver.domain.vehicle.dao;

import com.rms.drifeserver.domain.vehicle.model.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcVehicleRepository implements VehicleRepository{

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer save(Vehicle vehicle) {
        return null;
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
        return null;
    }

    private RowMapper<Vehicle> vehicleRowMapper() {
        return (rs, rowNum) -> {
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleSeq(rs.getInt("VEHICLE_SEQ"));
            vehicle.setUserSeq(rs.getInt("USER_SEQ"));
            vehicle.setVehicleNum(rs.getString("VEHICLE_NUM"));
            return vehicle;
        };
    }
}
