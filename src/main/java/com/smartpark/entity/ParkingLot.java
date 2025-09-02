package com.smartpark.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "parking_lots",
        uniqueConstraints = @UniqueConstraint(columnNames = "lot_id"))

public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lot_id", length = 50, nullable = false, unique = true)
    private String lotId;

    private String location;

    private int capacity;

    @Column(name = "occupied_spaces")

    private int occupiedSpaces;

    public ParkingLot() {}

    public ParkingLot(String lotId, String location, int capacity) {
        this.lotId = lotId;
        this.location = location;
        this.capacity = capacity;
        this.occupiedSpaces = 0;
    }
    //id
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    //LotId
    public String getLotId() {
        return lotId;
    }
    public void setLotId(String lotId) {
        this.lotId = lotId;
    }
    //location
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    //capacity
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    //
    public int getOccupiedSpaces() {
        return occupiedSpaces;
    }
    public void setOccupiedSpaces(int occupiedSpaces) {
        this.occupiedSpaces = occupiedSpaces;
    }
}
