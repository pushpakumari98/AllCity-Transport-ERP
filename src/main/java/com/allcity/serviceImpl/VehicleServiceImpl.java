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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class VehicleServiceImpl implements VehicleService {

    // ✅ ABSOLUTE IMAGE DIRECTORY (FIXED)
    private static final String IMAGE_DIRECTORY_BACKEND =
            System.getProperty("user.dir")
                    + File.separator + "uploads"
                    + File.separator + "images"
                    + File.separator;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<String> getAllOriginCity() {
        return List.of();
    }

    @Override
    public List<String> getAllDestinationCity() {
        return List.of();
    }

    // ================= ADD / UPDATE VEHICLE =================
    @Override
    public Response addVehicle(VehicleDTO dto, MultipartFile imageFile) {

        Vehicle vehicle = new Vehicle();

        // ✅ REQUIRED FIELDS (VALIDATION DEPENDS ON THESE)
        vehicle.setVehicleRegNo(dto.getVehicleRegNo());
        vehicle.setVehicleType(dto.getVehicleType());

        // ✅ OTHER FIELDS
        vehicle.setPermitLevel(dto.getPermitLevel());
        vehicle.setDriverMob(dto.getDriverMob());
        vehicle.setPrice(dto.getPrice());
        vehicle.setCapacity(dto.getCapacity());
        vehicle.setOriginCity(dto.getOriginCity());
        vehicle.setDestinationCity(dto.getDestinationCity());
        vehicle.setDescription(dto.getDescription());
        vehicle.setVehicleStatus(dto.getVehicleStatus());

        // ✅ IMAGE
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                vehicle.setImage(imageFile.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Image upload failed");
            }
        }

        vehicleRepository.save(vehicle); // ✅ save ONLY AFTER setting everything
        return new Response("Vehicle added successfully");
    }


    @Override
    public Response updateVehicle(Vehicle vehicle, MultipartFile imageFile) {

        Vehicle existing = vehicleRepository.findById(vehicle.getId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        existing.setVehicleRegNo(vehicle.getVehicleRegNo());
        existing.setPermitLevel(vehicle.getPermitLevel());
        existing.setDriverMob(vehicle.getDriverMob());
        existing.setVehicleType(vehicle.getVehicleType());
        existing.setPrice(vehicle.getPrice());
        existing.setCapacity(vehicle.getCapacity());
        existing.setOriginCity(vehicle.getOriginCity());
        existing.setDestinationCity(vehicle.getDestinationCity());
        existing.setDescription(vehicle.getDescription());
        existing.setVehicleStatus(vehicle.getVehicleStatus());

        // 🔥 CRITICAL FIX
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                existing.setImage(imageFile.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Image upload failed");
            }
        }
        // ❌ DO NOT touch image if no file uploaded

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

        List<Vehicle> vehicles =
                vehicleRepository.findByVehicleStatus(VehicleStatus.AVAILABLE);

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

    // ================= IMAGE SAVE METHOD (FINAL FIX) =================
    private String saveImage(MultipartFile imageFile) {

        if (imageFile == null || imageFile.isEmpty()) {
            return null;
        }

        if (!imageFile.getContentType().startsWith("image/")) {
            throw new IllegalArgumentException("Only image files are allowed");
        }

        File directory = new File(IMAGE_DIRECTORY_BACKEND);

        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (!created) {
                throw new RuntimeException(
                        "Failed to create directory: " + IMAGE_DIRECTORY_BACKEND);
            }
        }

        String uniqueFileName =
                UUID.randomUUID() + "_" + imageFile.getOriginalFilename();

        File destination = new File(directory, uniqueFileName);

        try {
            imageFile.transferTo(destination);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save image: " + e.getMessage());
        }

        // ✅ Return URL path (served via WebConfig)
        return "/images/" + uniqueFileName;
    }
}
