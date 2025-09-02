package com.smartpark.controller;


import com.smartpark.dto.CheckInRequest;
import com.smartpark.dto.CheckOutRequest;
import com.smartpark.entity.ParkingSession;
import com.smartpark.service.ParkingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/parking")
public class ParkingController {
    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {

        this.parkingService = parkingService;
    }

    @PostMapping("/checkin")
    public ResponseEntity<?> checkIn(@Valid @RequestBody CheckInRequest req) {
        ParkingSession session = parkingService.checkIn(req.getLotId(),req.getLicensePlate());
        return ResponseEntity.ok(session);
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkOut(@Valid @RequestBody CheckOutRequest req) {
        ParkingSession session = parkingService.checkOut(req.getLicensePlate());
        return ResponseEntity.ok(session);
    }
}
