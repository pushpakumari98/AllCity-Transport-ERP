package com.allcity.controller;

import com.allcity.dtos.Response;
import com.allcity.dtos.VehicleDTO;
import com.allcity.entities.Vehicle;
import com.allcity.enums.VehicleStatus;
import com.allcity.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin(origins = {"http://localhost:59753","https://allcity-transport-erp.onrender.com","https://allcity-transport-erp-frontend.onrender.com"})
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    // ================= ADD VEHICLE =================
    @PostMapping(
            value = "/add-vehicle",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> addVehicle(
            @ModelAttribute VehicleDTO vehicleDTO,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        return ResponseEntity.ok(vehicleService.addVehicle(vehicleDTO, image));
    }


    // ================= UPDATE VEHICLE =================
    @PutMapping(
            value = "/update/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateVehicle(
            @PathVariable Long id,
            @ModelAttribute  Vehicle vehicle,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        vehicle.setId(id);
        return ResponseEntity.ok(vehicleService.updateVehicle(vehicle, image));
    }

    // ================= GET AVAILABLE VEHICLES =================
    @GetMapping("/available")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','USER')")
    public ResponseEntity<Response> getAvailableVehicles() {
        return ResponseEntity.ok(vehicleService.getAvailableVehicles());
    }

    // ================= GET ALL VEHICLES =================
    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    // ================= GET VEHICLES BY STATUS =================
    @GetMapping("/status/{status}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public ResponseEntity<List<Vehicle>> getVehiclesByStatus(
            @PathVariable VehicleStatus status) {
        return ResponseEntity.ok(vehicleService.getVehiclesByStatus(status));
    }

    // ================= GET ALL ORIGIN CITIES =================
    @GetMapping("/origin-cities")
    public ResponseEntity<List<String>> getAllOriginCity() {
        return ResponseEntity.ok(vehicleService.getAllOriginCity());
    }

    // ================= GET ALL DESTINATION CITIES =================
    @GetMapping("/destination-cities")
    public ResponseEntity<List<String>> getAllDestinationCity() {
        return ResponseEntity.ok(vehicleService.getAllDestinationCity());
    }

    // ================= DELETE VEHICLE =================
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteVehicle(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.deleteVehicle(id));
    }
}
