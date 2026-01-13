package com.allcity.repositories;

import com.allcity.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findBySerialNo(Long serialNo);


    List<Driver> findByCarryMaterialType(String carryMaterialType);

    List<Driver> findByVehicleNo(String vehicleNo);
}
