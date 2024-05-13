package com.wheelzhub.demo.vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query("SELECT v FROM Vehicle v JOIN v.rents r WHERE r.user.id = :userId")
    List<Vehicle> findAllVehiclesByUserId(Long userId);
}
