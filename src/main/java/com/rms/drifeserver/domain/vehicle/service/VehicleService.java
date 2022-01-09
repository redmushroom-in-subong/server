package com.rms.drifeserver.domain.vehicle.service;

import com.rms.drifeserver.domain.vehicle.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    Integer saveVehicle(Vehicle vehicle);
    List<Vehicle> findAllVehicles();
    Optional<Vehicle> findVehicleByNum(String vehicleNum);
    List<Vehicle> findVehicleByUser(Integer userSeq);
    Integer deleteVehicle(Vehicle vehicle);
}
