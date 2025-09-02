package com.smartpark.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "parking_sessions")
public class ParkingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vehicle_license", referencedColumnName = "license_plate")
    private Vehicle vehicle;

    @ManyToOne(optional = false)
    @JoinColumn(name = "parking_lot_id")
    private ParkingLot parkingLot;

    @Column(name = "check_in_time")
    private LocalDateTime checkInTime;

    @Column(name = "check_out_time")
    private LocalDateTime checkOutTime;

    public ParkingSession() {}

    public ParkingSession(Vehicle vehicle, ParkingLot parkingLot, LocalDateTime checkInTime) {
        this.vehicle = vehicle;
        this.parkingLot = parkingLot;
        this.checkInTime = checkInTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalDateTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public boolean isActive() {
        return checkOutTime != null;
    }
}
