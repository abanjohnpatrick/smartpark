package com.smartpark.controller;


import com.smartpark.dto.RegisterLotRequest;
import com.smartpark.entity.ParkingLot;
import com.smartpark.entity.Vehicle;
import com.smartpark.repository.ParkingLotRepository;
import com.smartpark.service.ParkingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/lots")
@Validated
public class LotController {
    private final ParkingService parkingService;
    private final ParkingLotRepository lotRepo;

    public LotController(ParkingService parkingService, ParkingLotRepository lotRepo) {
        this.parkingService = parkingService;
        this.lotRepo = lotRepo;

    }

    @PostMapping
    public ResponseEntity<?> registerLot(
            @Valid
            @RequestBody
            RegisterLotRequest req){
        ParkingLot lot = new ParkingLot(req.getLotId(), req.getLocation(), req.getCapacity());

        ParkingLot saved = parkingService.registerLot(lot);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping("/{lotId}/occupancy")
    public ResponseEntity<?> getOccupancy(@PathVariable String lotId) {
        ParkingLot lot = lotRepo.findByLotId(lotId).orElseThrow(() -> new IllegalArgumentException("Parking Lot Not Found"));
        int available  = lot.getCapacity() - lot.getOccupiedSpaces();
        return ResponseEntity.ok(Map.of(
                "lotId", lot.getLotId(),
                "capacity", lot.getCapacity(),
                "occupiedSpaces", lot.getOccupiedSpaces(),
                "availableSpaces", available
        ));
    }

    @GetMapping("/{lotId}/vehicles")
    public ResponseEntity<?> getVehicles(@PathVariable String lotId) {
        ParkingLot lot = lotRepo.findByLotId(lotId).orElseThrow(() -> new IllegalArgumentException("Parking Lot Not Found"));
        return ResponseEntity.ok(parkingService.getActiveSessionsInLot(lot).stream()
                .map(s -> s.getVehicle())
                .toArray());
    }

}
