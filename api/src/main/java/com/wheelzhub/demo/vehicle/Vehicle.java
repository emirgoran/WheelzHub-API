package com.wheelzhub.demo.vehicle;

import com.wheelzhub.demo.DatabaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Vehicle implements DatabaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String make;
    private String model;
    private int year;
    private String licensePlate;

    @Override
    public VehicleDto toDto() {
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setId(id);
        vehicleDto.setMake(make);
        vehicleDto.setModel(model);
        vehicleDto.setYear(year);
        vehicleDto.setLicensePlate(licensePlate);
        return vehicleDto;
    }
}
