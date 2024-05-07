package com.wheelzhub.demo.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<VehicleDto> searchVehicles(String make, String model, String licensePlate) {
        Specification<Vehicle> spec = Specification
                .where(VehicleSpecifications.hasMake(make))
                .and(VehicleSpecifications.hasModel(model))
                .and(VehicleSpecifications.hasLicensePlate(licensePlate));

        return vehicleRepository.findAll(spec).stream().map(vehicle -> vehicle.toDto()).collect(Collectors.toList());
    }

    public List<VehicleDto> getAllVehicles() {
        return vehicleRepository
                .findAll()
                .stream()
                .map(vehicle -> vehicle.toDto())
                .collect(Collectors.toList());
    }

    public VehicleDto createVehicle(VehicleDto vehicleDto) {
        Vehicle vehicle = vehicleDto.toDatabaseEntity();

        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        vehicleDto.setId(savedVehicle.getId());

        return vehicleDto;
    }

    public VehicleDto getVehicleById(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle with ID " + id + " not found."));

        return vehicle.toDto();
    }

    public boolean deleteVehicleById(Long id) {
        if (vehicleRepository.existsById(id)) {
            vehicleRepository.deleteById(id);
            return true;
        }
        return false;
    }
}