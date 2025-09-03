package com.smartpark.repository;

import com.smartpark.entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, String> {
    Optional<ParkingLot> findByLotId(String lotId);
}
