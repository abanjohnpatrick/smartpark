package com.smartpark.dto;

import jakarta.validation.constraints.NotBlank;

public class CheckInRequest {
    @NotBlank
    private String lotId;

    @NotBlank
    private String licensePlate;

    public String getLotId() {
        return lotId;
    }

    public void setLotId(String lotId) {
        this.lotId = lotId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}


