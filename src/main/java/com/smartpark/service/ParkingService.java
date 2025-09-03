package com.smartpark.service;

import com.smartpark.entity.ParkingLot;
import com.smartpark.entity.ParkingSession;
import com.smartpark.entity.Vehicle;
import com.smartpark.exception.ApiException;
import com.smartpark.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParkingService {
    private final ParkingLotRepository lotRepo;
    private final VehicleRepository vehicleRepo;
    private final ParkingSessionRepository sessionRepo;

    public ParkingService(ParkingLotRepository lotRepo,
                          VehicleRepository vehicleRepo,
                          ParkingSessionRepository sessionRepo) {

        this.lotRepo = lotRepo;
        this.vehicleRepo = vehicleRepo;
        this.sessionRepo = sessionRepo;
    }

    @Transactional
    public ParkingLot registerLot(ParkingLot lot) {
        return lotRepo.save(lot);
    }

    @Transactional
    public Vehicle registerVehicle(Vehicle v) {
        return vehicleRepo.save(v);
    }

    public ParkingLot getLot(String lotId) {
        return lotRepo.findById(lotId)
                .orElseThrow(() -> new ApiException("Parking lot not found: " + lotId));
    }

    public Vehicle getVehicle(String licensePlate) {
        return vehicleRepo.findById(licensePlate)
                .orElseThrow(() -> new ApiException("Vehicle not found: " + licensePlate));
    }

    @Transactional
    public ParkingSession checkIn(String lotId, String licensePlate) {
        ParkingLot lot = lotRepo.findByLotId(lotId).orElseThrow(()
                -> new IllegalArgumentException("Parking Lot Not Found"));
        Vehicle vehicle = vehicleRepo.findById(licensePlate).orElseThrow(()
                -> new IllegalArgumentException("Vehicle Not registered"));

        if (lot.getOccupiedSpaces() >= lot.getCapacity()) {
            throw new IllegalStateException("Parking Lot Capacity Exceeded");
        }

        if(sessionRepo.findByVehicleAndCheckOutTimeIsNull(vehicle).isPresent()) {
            throw new IllegalStateException("Vehicle is already parked in a lot");
        }

        ParkingSession session = new ParkingSession(vehicle, lot, LocalDateTime.now());
        session = sessionRepo.save(session);

        lot.setOccupiedSpaces(lot.getOccupiedSpaces() + 1);
        lotRepo.save(lot);

        return session;
    }

    @Transactional
    public ParkingSession checkOut(String licensePlate) {
        Vehicle vehicle = vehicleRepo.findById(licensePlate).orElseThrow(()
                -> new IllegalArgumentException("Vehicle Not Registered"));

        ParkingSession session = sessionRepo.findByVehicleAndCheckOutTimeIsNull(vehicle).orElseThrow(()
                -> new IllegalStateException("Vehicle is not currently parked"));

        session.setCheckOutTime(LocalDateTime.now());
        session = sessionRepo.save(session);

        ParkingLot lot = session.getParkingLot();
        lot.setOccupiedSpaces(Math.max(0,lot.getOccupiedSpaces() - 1));
        lotRepo.save(lot);

        return session;
    }

    public List<ParkingSession> getActiveSessionsInLot(ParkingLot lot) {
        return sessionRepo.findByParkingLotAndCheckOutTimeIsNull(lot);
    }
}
