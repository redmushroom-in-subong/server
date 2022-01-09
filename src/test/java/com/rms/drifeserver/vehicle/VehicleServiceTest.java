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

        //when
        Vehicle findVehicle = vehicleService.findVehicleByNum("11가1111").get();

        //then
        Assertions.assertThat(findVehicle.getVehicleSeq()).isEqualTo(1);
    }

    @Test
    public void findByUser() {
        //given

        //when
        List<Vehicle> findVehicles= vehicleService.findVehicleByUser(1);

        //then
        Assertions.assertThat(findVehicles.size()).isEqualTo(2);
    }
}
