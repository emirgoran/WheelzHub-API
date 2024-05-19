package com.wheelzhub.demo.vehicle;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wheelzhub.demo.image.Image;
import com.wheelzhub.demo.rent.Rent;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
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
    @OrderColumn
    @JsonIgnore
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Rent> rents = new ArrayList<>();

    public void addRent(Rent rent) {
        rents.add(rent);
        rent.setVehicle(this);
    }

    public void removeRent(Rent rent) {
        rents.remove(rent);
        rent.setVehicle(null);
    }

    public void addImage(Image image) {
        images.add(image);
        image.setVehicle(this);
    }

    public void removeImage(Image image) {
        images.remove(image);
        image.setVehicle(null);
    }

    @JsonIgnore
    @Transient  // This method does not correspond to a database column.
    public Rent getActiveRent() {
        LocalDateTime today = LocalDateTime.now();
        return rents.stream()
                .filter(rent -> !today.isBefore(rent.getStartDateTime()) && !today.isAfter(rent.getEndDateTime()))
                .findFirst()
                .orElse(null);
    }

    @Transient  // This method does not correspond to a database column.
    public long[] getPhotosIDs() {
        return images.stream()
                .mapToLong(Image::getId)
                .toArray();
    }
}
