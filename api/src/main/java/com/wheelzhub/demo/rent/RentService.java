package com.wheelzhub.demo.rent;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RentService {

    @Autowired
    private RentRepository rentRepository;

    public Rent createRent(Rent rent) {
        Rent savedRent = rentRepository.save(rent);
        savedRent.getUser().addRent(savedRent);
        savedRent.getVehicle().addRent(savedRent);
        return savedRent;
    }

    public void deleteRent(Long id) {
        Rent rent = rentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rent with id " + id + " not found."));

        rent.getUser().removeRent(rent);
        rent.getVehicle().removeRent(rent);
        rentRepository.deleteById(id);
    }

    public List<Rent> getAllRents() {
        return rentRepository.findAll();
    }

    public Rent getRentById(Long id) {
        Rent rent = rentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rent with id " + id + " not found."));

        return rent;
    }

    public Rent updateRent(Rent updatedRent) {
        Rent rent = rentRepository.findById(updatedRent.getId())
                .orElseThrow(() -> new EntityNotFoundException("Rent with id " + updatedRent.getId() + " not found."));

        if (rent.getVehicle().getId() != updatedRent.getVehicle().getId()) {
            rent.getVehicle().removeRent(rent);
            updatedRent.getVehicle().addRent(rent);
        }

        if (rent.getUser().getId() != updatedRent.getUser().getId()) {
            rent.getUser().removeRent(rent);
            updatedRent.getUser().addRent(rent);
        }

        return rentRepository.save(updatedRent);
    }

    // Additional requests

    public List<Rent> getActiveRents() {
        return rentRepository.findActiveRents(LocalDateTime.now());
    }
}
