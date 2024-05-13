package com.wheelzhub.demo.vehicle;

import com.wheelzhub.demo.rent.Rent;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String make;
    private String model;
    private int year;
    private String licensePlate;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rent> rents = new ArrayList<>();

    public void addRent(Rent rent) {
        rents.add(rent);
        rent.setVehicle(this);
    }

    public void removeRent(Rent rent) {
        rents.remove(rent);
        rent.setVehicle(null);
    }
}
