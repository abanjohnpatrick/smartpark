package com.smartpark.dto;

import com.smartpark.entity.VehicleType;
import jakarta.validation.constraints.*;

public class RegisterVehicleRequest {
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9-]+$" , message = "Allowed characters: letters, numbers, dash")
    private String licensePlate;

    @NotNull
    private VehicleType type;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Ownder name: letters and spaces only")
    private String ownerName;

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
