package com.allcity.serviceImpl;

import com.allcity.dtos.Response;
import com.allcity.dtos.VehicleDTO;
import com.allcity.entities.Vehicle;
import com.allcity.enums.VehicleStatus;
import com.allcity.repositories.VehicleRepository;
import com.allcity.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<String> getAllOriginCity() {
        // Example: fetch distinct origin cities from DB
        return vehicleRepository.findDistinctOriginCities();
    }

    @Override
    public List<String> getAllDestinationCity() {
        // Example: fetch distinct destination cities from DB
        return vehicleRepository.findDistinctDestinationCities();
    }

    // ================= ADD VEHICLE =================
    @Override
    public Response addVehicle(VehicleDTO dto) {

        Vehicle vehicle = new Vehicle();

        vehicle.setVehicleRegNo(dto.getVehicleRegNo());
        vehicle.setVehicleType(dto.getVehicleType());
        vehicle.setPermitLevel(dto.getPermitLevel());
        vehicle.setDriverMob(dto.getDriverMob());
        vehicle.setPrice(dto.getPrice());
        vehicle.setCapacity(dto.getCapacity());
        vehicle.setOriginCity(dto.getOriginCity());
        vehicle.setDestinationCity(dto.getDestinationCity());
        vehicle.setDescription(dto.getDescription());
        vehicle.setVehicleStatus(dto.getVehicleStatus());

        vehicleRepository.save(vehicle);

        return new Response("Vehicle added successfully");
    }


    // ================= UPDATE VEHICLE =================
    @Override
    public Response updateVehicle(Long id, VehicleDTO dto) {

        Vehicle existing = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        existing.setVehicleRegNo(dto.getVehicleRegNo());
        existing.setVehicleType(dto.getVehicleType());
        existing.setPermitLevel(dto.getPermitLevel());
        existing.setDriverMob(dto.getDriverMob());
        existing.setPrice(dto.getPrice());
        existing.setCapacity(dto.getCapacity());
        existing.setOriginCity(dto.getOriginCity());
        existing.setDestinationCity(dto.getDestinationCity());
        existing.setDescription(dto.getDescription());

        // 🔥 THIS LINE UPDATES STATUS
        existing.setVehicleStatus(dto.getVehicleStatus());

        vehicleRepository.save(existing);

        return new Response("Vehicle updated successfully");
    }


    // ================= GET ALL VEHICLES =================
    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // ================= GET AVAILABLE VEHICLES =================
    @Override
    public Response getAvailableVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findByVehicleStatus(VehicleStatus.AVAILABLE);

        List<VehicleDTO> vehicleDTOList = modelMapper.map(
                vehicles,
                new TypeToken<List<VehicleDTO>>() {}.getType()
        );

        return Response.builder()
                .status(200)
                .message("success")
                .vehicleDTOS(vehicleDTOList)
                .build();
    }

    // ================= GET VEHICLES BY STATUS =================
    @Override
    public List<Vehicle> getVehiclesByStatus(VehicleStatus status) {
        return vehicleRepository.findByVehicleStatus(status);
    }

    // ================= DELETE VEHICLE =================
    @Override
    public Response deleteVehicle(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        vehicleRepository.delete(vehicle);

        return Response.builder()
                .status(200)
                .message("Vehicle deleted successfully")
                .build();
    }
}
