package com.smartpark.dto;

import jakarta.validation.constraints.*;


public class RegisterLotRequest {
    @NotBlank
    @Size(max = 50)
    private String lotId;

    private String location;

    @Min(1)
    private  int capacity;

    public String getLotId() {
        return lotId;
    }

    public void setLotId(String lotId) {
        this.lotId = lotId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
