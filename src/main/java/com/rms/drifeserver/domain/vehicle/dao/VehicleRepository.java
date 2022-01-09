package com.rms.drifeserver.domain.vehicle.dao;

import com.rms.drifeserver.domain.vehicle.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository {
    Integer save(Vehicle vehicle);
    List<Vehicle> findAll();
    Optional<Vehicle> findByNum(String vehicleNum);
    List<Vehicle> findByUser(Integer userSeq);
    Integer deleteVehicle(Vehicle vehicle);
}
