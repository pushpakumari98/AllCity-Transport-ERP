package com.allcity.service;

import com.allcity.dtos.Response;
import com.allcity.dtos.VehicleDTO;
import com.allcity.entities.Vehicle;
import com.allcity.enums.VehicleStatus;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;



public interface VehicleService {

        Response addVehicle(VehicleDTO vehicle, MultipartFile image);

        Response updateVehicle(Vehicle vehicle, MultipartFile image);

        Response getAvailableVehicles();

        List<Vehicle> getAllVehicles();

        List<Vehicle> getVehiclesByStatus(VehicleStatus status);

        List<String> getAllOriginCity();

        List<String> getAllDestinationCity();

        Response deleteVehicle(Long id);
}
