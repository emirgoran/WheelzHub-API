package com.wheelzhub.demo.vehicle;

import com.wheelzhub.demo.Dto;
import lombok.Data;

@Data
public class VehicleDto implements Dto {
    private Long id;
    private String make;
    private String model;
    private int year;
    private String licensePlate;

    @Override
    public String toString() {
        return "VehicleDto{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", licensePlate='" + licensePlate + '\'' +
                '}';
    }

    @Override
    public Vehicle toDatabaseEntity() {
        Vehicle vehicle = new Vehicle();
        vehicle.setMake(make);
        vehicle.setModel(model);
        vehicle.setYear(year);
        vehicle.setLicensePlate(licensePlate);
        return vehicle;
    }
}
