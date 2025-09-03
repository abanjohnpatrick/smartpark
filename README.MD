# SmartPark — Parking Management System

SmartPark is a Spring Boot application that provides a REST API for managing parking lots and vehicles.  
It uses an H2 in-memory database and comes with preloaded data for testing.


## Build Instructions
```bash
mvn clean package
```
This compiles the project and runs unit tests. The final JAR will be in the `target/` directory.

---

## Run Instructions
```bash
mvn spring-boot:run
```
or
```bash
java -jar target/smartpark-0.0.1-SNAPSHOT.jar
```

- The app will start on **http://localhost:8080**
- The H2 Console is available at **http://localhost:8080/h2-console**
  - JDBC URL: `jdbc:h2:mem:smartpark`
  - User: `sa`
  - Password: *(leave blank)*

---

## Test Instructions

### 1. Unit / Integration Tests
Run:
```bash
mvn test
```
This will execute all JUnit tests.

### 2. API Testing with Postman
1. Import the provided file `smartpark_postman_collection.json` into Postman.
2. Run requests directly:
   - Register parking lot
   - Register vehicle
   - Check-in / Check-out vehicle
   - View occupancy
   - View vehicles in lot

### 3. API Testing with curl

Register a parking lot:
```bash
curl -X POST http://localhost:8080/api/lots \
  -H 'Content-Type: application/json' \
  -d '{"lotId":"LOT-C1","location":"Race Track","capacity":100}'
```

Register a vehicle:
```bash
curl -X POST http://localhost:8080/api/vehicles \
  -H 'Content-Type: application/json' \
  -d '{"licensePlate":"MAX-333","type":"CAR","ownerName":"Max Verstappen"}'
```

Check-in a vehicle:
```bash
curl -X POST http://localhost:8080/api/parking/checkin \
  -H 'Content-Type: application/json' \
  -d '{"lotId":"LOT-C1","licensePlate":"MAX-333"}'
```

Check-out a vehicle:
```bash
curl -X POST http://localhost:8080/api/parking/checkout \
  -H 'Content-Type: application/json' \
  -d '{"lotId":"LOT-C1","licensePlate":"MAX-333"}'
```

View occupancy:
```bash
curl http://localhost:8080/api/lots/LOT-C1/occupancy
```

View vehicles in a lot:
```bash
curl http://localhost:8080/api/lots/LOT-C1/vehicles
```
