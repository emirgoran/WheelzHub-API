package com.wheelzhub.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsernameAndPassword(String username, String password);

    @Query("SELECT u FROM User u JOIN u.rents r WHERE r.vehicle.id = :vehicleId")
    List<User> findAllUsersByVehicleId(Long vehicleId);
}
