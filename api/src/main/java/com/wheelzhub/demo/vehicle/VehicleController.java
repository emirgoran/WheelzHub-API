package com.wheelzhub.demo.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Returning the DTOs directly instead of wrapping them in ResponseEntity for code simplicity.
// Not the best practice since, in addition to the DTO, the ResponseEntity returns HTTP status and (potentially) custom headers.

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public List<VehicleDto> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{id}")
    public VehicleDto getVehicleById(@PathVariable Long id) {
        return vehicleService.getVehicleById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VehicleDto> updateVehicle(@RequestBody VehicleDto vehicleDto) {

        VehicleDto updatedVehicle = vehicleService.updateVehicle(vehicleDto);
        return ResponseEntity.ok(updatedVehicle);
    }

    @PostMapping
    public VehicleDto createVehicle(@RequestBody VehicleDto vehicleDto) {
        Vehicle savedVehicle = vehicleService.createVehicle(vehicleDto).toDatabaseEntity();
        return savedVehicle.toDto();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        if (vehicleService.deleteVehicleById(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public List<VehicleDto> searchVehicles(
            @RequestParam(required = false) String make,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String licensePlate
    ) {
        return vehicleService.searchVehicles(make, model, licensePlate);
    }
}
