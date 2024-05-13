package com.wheelzhub.demo.rent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
@RestController
@RequestMapping("/api/rents")
public class RentController {

    @Autowired
    private RentService rentService;

    @PostMapping
    public Rent createRent(@RequestBody Rent rent) {
        return rentService.createRent(rent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRent(@PathVariable Long id) {
        rentService.deleteRent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public List<Rent> getAllRents() {
        return rentService.getAllRents();
    }

    @GetMapping("/{id}")
    public Rent getRentById(@PathVariable Long id) {
        return rentService.getRentById(id);
    }

    @PutMapping("/{id}")
    public Rent updateRent(@PathVariable Long id, @RequestBody Rent rentDetails) {
        return rentService.updateRent(rentDetails);
    }

    // Additional requests

    @GetMapping("/active")
    public List<Rent> getActiveRents() {
        return rentService.getActiveRents();
    }
}
