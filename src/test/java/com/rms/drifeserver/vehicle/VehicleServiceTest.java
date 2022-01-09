package com.rms.drifeserver.vehicle;

import com.rms.drifeserver.domain.vehicle.model.Vehicle;
import com.rms.drifeserver.domain.vehicle.service.VehicleService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class VehicleServiceTest {

    @Autowired
    VehicleService vehicleService;

    @Test
    public void findByNum() {
        //given
        Vehicle vehicle = new Vehicle(2, "12가1234");
        vehicleService.saveVehicle(vehicle);
        //when
        Vehicle findVehicle = vehicleService.findVehicleByNum("12가1234").get();

        //then
        Assertions.assertThat(findVehicle.getUserSeq()).isEqualTo(2);
    }

    @Test
    public void findByUser() {
        //given
        Vehicle vehicle = new Vehicle(3, "12가1234");
        vehicleService.saveVehicle(vehicle);
        //when
        List<Vehicle> findVehicles= vehicleService.findVehicleByUser(3);

        //then
        Assertions.assertThat(findVehicles.size()).isEqualTo(1);
    }
}
