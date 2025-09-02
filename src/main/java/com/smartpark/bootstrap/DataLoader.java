package com.smartpark.bootstrap;

import com.smartpark.entity.ParkingLot;
import com.smartpark.entity.Vehicle;
import com.smartpark.entity.VehicleType;
import com.smartpark.repository.ParkingLotRepository;
import com.smartpark.repository.VehicleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final ParkingLotRepository lotRepo;
    private final VehicleRepository vehicleRepo;

    public DataLoader(ParkingLotRepository lotRepo, VehicleRepository vehicleRepo) {
        this.lotRepo = lotRepo;
        this.vehicleRepo = vehicleRepo;

    }

    public void run(String... args) throws Exception{
        if (lotRepo.count() ==0){
            lotRepo.save(new ParkingLot("LOT-A1","Office", 5));
            lotRepo.save(new ParkingLot("LOT-B1","Mall",50));
        }

        if (vehicleRepo.count() ==0){
            vehicleRepo.save(new Vehicle("ABC123", VehicleType.CAR, "John Aban"));
            vehicleRepo.save(new Vehicle("222ORM", VehicleType.MOTORCYCLE, "Patrick Aban"));
        }

    }

}
