package com.wheelzhub.demo.rent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RentRepository extends JpaRepository<Rent, Long> {

    @Query("SELECT r FROM Rent r WHERE r.startDateTime <= :currentDate AND r.endDateTime >= :currentDate")
    List<Rent> findActiveRents(@Param("currentDate") LocalDateTime currentDate);
}