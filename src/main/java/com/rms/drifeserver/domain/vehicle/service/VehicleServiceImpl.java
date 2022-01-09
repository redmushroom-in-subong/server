package com.rms.drifeserver.domain.vehicle.service;

import com.rms.drifeserver.domain.vehicle.dao.VehicleRepository;
import com.rms.drifeserver.domain.vehicle.model.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService{

    private final VehicleRepository vehicleRepository;

    @Override
    public Integer saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public List<Vehicle> findAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Optional<Vehicle> findVehicleByNum(String vehicleNum) {
        return vehicleRepository.findByNum(vehicleNum);
    }

    @Override
    public List<Vehicle> findVehicleByUser(Integer userSeq) {
        return vehicleRepository.findByUser(userSeq);
    }

    @Override
    public Integer deleteVehicle(Vehicle vehicle) {
        return vehicleRepository.deleteVehicle(vehicle);
    }
}
