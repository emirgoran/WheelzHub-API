package com.wheelzhub.demo.vehicle;

import com.wheelzhub.demo.rent.Rent;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VehicleDTO {

    public VehicleDTO(Vehicle vehicle) {
        this.id = vehicle.getId();
        this.make = vehicle.getMake();
        this.model = vehicle.getModel();
        this.year = vehicle.getYear();
        this.licensePlate = vehicle.getLicensePlate();

        Rent activeRent;

        if ((activeRent = vehicle.getActiveRent()) != null) {
            this.rented =
                    LocalDateTime.now().isBefore(activeRent.getEndDateTime()) &&
                            LocalDateTime.now().isAfter(activeRent.getStartDateTime());
            this.startRentDateTime = activeRent.getStartDateTime();
            this.endRentDateTime = activeRent.getEndDateTime();
        }
    }

    private Long id;
    private String make;
    private String model;
    private int year;
    private String licensePlate;
    private boolean rented;
    private LocalDateTime startRentDateTime;
    private LocalDateTime endRentDateTime;
}
