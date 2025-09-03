package com.smartpark;

import com.smartpark.entity.ParkingLot;
import com.smartpark.exception.ApiException;
import com.smartpark.repository.ParkingLotRepository;
import com.smartpark.service.ParkingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParkingLotServiceTest {

    @Mock
    private ParkingLotRepository lotRepo;

    @InjectMocks
    private ParkingService lotService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // initializes mocks
    }

    @Test
    void testRegisterParkingLot() {
        ParkingLot lot = new ParkingLot("LOT-Z1", "Mall", 100);
        when(lotRepo.save(lot)).thenReturn(lot);

        ParkingLot saved = lotService.registerLot(lot);

        assertNotNull(saved);
        assertEquals("LOT-Z1", saved.getLotId());
        assertEquals("Mall", saved.getLocation());
        verify(lotRepo, times(1)).save(lot);
    }

    @Test
    void testGetParkingLotFound() {
        ParkingLot lot = new ParkingLot("LOT-Z1", "Mall", 100);
        when(lotRepo.findById("LOT-Z1")).thenReturn(Optional.of(lot));

        ParkingLot found = lotService.getLot("LOT-Z1");

        assertEquals("Mall", found.getLocation());
        verify(lotRepo, times(1)).findById("LOT-Z1");
    }

    @Test
    void testGetParkingLotNotFound() {
        when(lotRepo.findById("LOT-XX")).thenReturn(Optional.empty());

        assertThrows(ApiException.class,
                () -> lotService.getLot("LOT-XX"));
    }
}
