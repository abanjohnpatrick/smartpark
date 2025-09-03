package com.smartpark;

import com.smartpark.entity.Vehicle;
import com.smartpark.entity.VehicleType;
import com.smartpark.repository.VehicleRepository;
import com.smartpark.service.ParkingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepo;

    @InjectMocks
    private ParkingService vehicleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterVehicle() {
        Vehicle vehicle = new Vehicle("ABC-123", VehicleType.CAR, "John Patrick");
        when(vehicleRepo.save(vehicle)).thenReturn(vehicle);

        Vehicle saved = vehicleService.registerVehicle(vehicle);

        assertEquals("ABC-123", saved.getLicensePlate());
        assertEquals(VehicleType.CAR, saved.getType());
        assertEquals("John Patrick", saved.getOwnerName());
        verify(vehicleRepo, times(1)).save(vehicle);
    }
}
