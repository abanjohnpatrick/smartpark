package com.smartpark.repository;

import com.smartpark.entity.ParkingLot;
import com.smartpark.entity.ParkingSession;
import com.smartpark.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParkingSessionRepository  extends JpaRepository<ParkingSession, String> {
    List<ParkingSession> findByParkingLotAndCheckOutTimeIsNull(ParkingLot lot);
    Optional<ParkingSession> findByVehicleAndCheckOutTimeIsNull(Vehicle vehicle);
}
