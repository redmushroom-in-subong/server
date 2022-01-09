package com.rms.drifeserver.domain.vehicle.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Vehicle {
    private Integer vehicleSeq;
    private Integer userSeq;
    private String vehicleNum;

    public Vehicle(Integer vehicleSeq, Integer userSeq, String vehicleNum) {
        this.vehicleSeq = vehicleSeq;
        this.userSeq = userSeq;
        this.vehicleNum = vehicleNum;
    }

    public Vehicle() {

    }
}
