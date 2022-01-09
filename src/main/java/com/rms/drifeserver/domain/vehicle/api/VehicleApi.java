package com.rms.drifeserver.domain.vehicle.api;

import com.rms.drifeserver.domain.vehicle.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class VehicleApi {
    private final VehicleService vehicleService;


}
