package com.allcity.serviceImpl;

import com.allcity.entities.Driver;
import com.allcity.repositories.DriverRepository;
import com.allcity.service.DriverService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;

    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public Driver saveDriver(Driver driver) {

        Long lastSerial = driverRepository.findMaxSerialNo();
        driver.setSerialNo(lastSerial == null ? 1 : lastSerial + 1);
        if (driver.getVehicleNo() != null && !driver.getVehicleNo().isBlank()) {
            driver.setVehicleNo(driver.getVehicleNo().toUpperCase());
        }

        return driverRepository.save(driver);
    }


    @Override
    public Driver updateDriver(Long id, Driver driver) {

        Driver existing = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found with ID: " + id));

        // ❌ NEVER update serialNo
        // existing.setSerialNo(driver.getSerialNo());

        existing.setDate(driver.getDate());
        existing.setVehicleNo(driver.getVehicleNo());
        existing.setStartedFrom(driver.getStartedFrom());
        existing.setDestination(driver.getDestination());
        existing.setContactNumber(driver.getContactNumber());
        existing.setAddress(driver.getAddress());
        existing.setDriverName(driver.getDriverName());
        existing.setCarryMaterialType(driver.getCarryMaterialType());

        return driverRepository.save(existing);
    }

    @Override
    public Driver getDriverById(Long id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found with ID: " + id));
    }

    @Override
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    @Override
    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }

    @Override
    public Driver getBySerialNumber(Long serialNo) {
        return driverRepository.findBySerialNo(serialNo)
                .orElseThrow(() -> new RuntimeException("Driver not found with serialNo: " + serialNo));
    }

    @Override
    public List<Driver> getByCarryMaterialType(String carryMaterialType) {
        return driverRepository.findByCarryMaterialType(carryMaterialType);
    }

    @Override
    public List<Driver> getByVehicleNumber(String vehicleNo) {
        return driverRepository.findByVehicleNo(vehicleNo);
    }


}
