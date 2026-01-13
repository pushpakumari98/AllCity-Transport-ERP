package com.allcity.service;

import com.allcity.entities.Driver;

import java.math.BigInteger;
import java.util.List;

public interface DriverService {

    Driver saveDriver(Driver driver);

    Driver updateDriver(Long id, Driver driver);

    Driver getDriverById(Long id);

    List<Driver> getAllDrivers();

    void deleteDriver(Long id);

    Driver getBySerialNumber(Long serialNo);

    List<Driver> getByCarryMaterialType(String carryMaterialType);

    List<Driver> getByVehicleNumber(String vehicleNo);
}
