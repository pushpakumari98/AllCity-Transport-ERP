package com.allcity.repositories;

import com.allcity.entities.Vehicle;
import com.allcity.enums.VehicleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    // Find by unique vehicle ID
    Optional<Vehicle> findByVehicleId(String vehicleId);

    // Find all vehicles by status
    List<Vehicle> findByVehicleStatus(VehicleStatus vehicleStatus);

    // ✅ Distinct origin cities
    @Query("SELECT DISTINCT v.originCity FROM Vehicle v")
    List<String> findDistinctOriginCities();

    // ✅ Distinct destination cities
    @Query("SELECT DISTINCT v.destinationCity FROM Vehicle v")
    List<String> findDistinctDestinationCities();
}
