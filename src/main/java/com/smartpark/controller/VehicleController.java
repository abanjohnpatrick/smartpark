package com.smartpark.controller;


import com.smartpark.dto.RegisterVehicleRequest;
import com.smartpark.entity.ParkingLot;
import com.smartpark.entity.Vehicle;
import com.smartpark.service.ParkingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    private final ParkingService parkingService;

    public VehicleController(ParkingService parkingService){
        this.parkingService = parkingService;
    }

    @PostMapping
    public ResponseEntity<?> registerVehicle(@Valid @RequestBody RegisterVehicleRequest req){
        Vehicle v = new Vehicle(req.getLicensePlate(), req.getType(), req.getOwnerName());

        Vehicle saved = parkingService.registerVehicle(v);
        return ResponseEntity.status(201).body(saved);
    }
}
