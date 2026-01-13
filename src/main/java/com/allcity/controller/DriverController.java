package com.allcity.controller;

import com.allcity.entities.Driver;
import com.allcity.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api/drivers")
@CrossOrigin(origins = {"http://localhost:59753","https://allcity-transport-erp.onrender.com"})
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    // ✅ CREATE DRIVER
    @PostMapping
    public ResponseEntity<Driver> createDriver(@Valid @RequestBody Driver driver) {
        return ResponseEntity.ok(driverService.saveDriver(driver));
    }

    // ✅ UPDATE DRIVER
    @PutMapping("/{id}")
    public ResponseEntity<Driver> updateDriver(
            @PathVariable Long id,
            @Valid @RequestBody Driver driver) {

        return ResponseEntity.ok(driverService.updateDriver(id, driver));
    }

    // ✅ GET DRIVER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable Long id) {
        return ResponseEntity.ok(driverService.getDriverById(id));
    }

    // ✅ GET ALL DRIVERS
    @GetMapping
    public ResponseEntity<List<Driver>> getAllDrivers() {
        return ResponseEntity.ok(driverService.getAllDrivers());
    }

    // ✅ DELETE DRIVER
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDriver(@PathVariable Long id) {
        driverService.deleteDriver(id);
        return ResponseEntity.ok("Driver deleted successfully");
    }

    // ✅ SEARCH BY SERIAL NUMBER
    @GetMapping("/serial/{serialNo}")
    public ResponseEntity<Driver> getBySerialNumber(@PathVariable Long serialNo) {
        return ResponseEntity.ok(driverService.getBySerialNumber(serialNo));
    }

    // ✅ SEARCH BY CARRY MATERIAL TYPE
    @GetMapping("/material/{carryMaterialType}")
    public ResponseEntity<List<Driver>> getByCarryMaterialType(
            @PathVariable String carryMaterialType) {

        return ResponseEntity.ok(
                driverService.getByCarryMaterialType(carryMaterialType)
        );
    }

    // ✅ SEARCH BY VEHICLE NUMBER
    @GetMapping("/vehicle/{vehicleNo}")
    public ResponseEntity<List<Driver>> getByVehicleNumber(
            @PathVariable String vehicleNo) {

        return ResponseEntity.ok(
                driverService.getByVehicleNumber(vehicleNo)
        );
    }
}
