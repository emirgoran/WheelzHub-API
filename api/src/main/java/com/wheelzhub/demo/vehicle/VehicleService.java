package com.wheelzhub.demo.vehicle;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicles() {
        return new ArrayList<>(vehicleRepository.findAll());
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        vehicle.setId(0L); // Forces creation of a new vehicle.

        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        return savedVehicle;
    }

    public Vehicle updateVehicle(Vehicle vehicle) {
        vehicleRepository.findById(vehicle.getId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle with ID " + vehicle.getId() + " not found."));

        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        return savedVehicle;
    }

    public Vehicle getVehicleById(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle with ID " + id + " not found."));

        return vehicle;
    }

    public void deleteVehicleById(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle with id " + id + " not found."));

        vehicleRepository.delete(vehicle);
    }

    // Additional requests

    // Find all vehicles that have been rented by user with userId.
    public List<Vehicle> getVehiclesByUserId(Long userId) {
        return vehicleRepository.findAllVehiclesByUserId(userId);
    }
}