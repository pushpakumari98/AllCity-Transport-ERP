package com.allcity.service;

import com.allcity.dtos.Response;
import com.allcity.dtos.VehicleDTO;
import com.allcity.entities.Vehicle;
import com.allcity.enums.VehicleStatus;

import java.util.List;

public interface VehicleService {

        // Add vehicle
        Response addVehicle(VehicleDTO dto);

        // Update vehicle
        Response updateVehicle(Long id, VehicleDTO vehicle);

        // Get available vehicles
        Response getAvailableVehicles();

        // Get all vehicles
        List<Vehicle> getAllVehicles();

        // Get vehicles by status
        List<Vehicle> getVehiclesByStatus(VehicleStatus status);

        // Get all origin cities
        List<String> getAllOriginCity();

        // Get all destination cities
        List<String> getAllDestinationCity();

        // Delete vehicle
        Response deleteVehicle(Long id);
}
