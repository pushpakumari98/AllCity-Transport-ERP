package com.allcity.controller;

import com.allcity.dtos.Response;
import com.allcity.dtos.VehicleDTO;
import com.allcity.entities.Vehicle;
import com.allcity.enums.VehicleStatus;
import com.allcity.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin(origins = {
        "http://localhost:59753",
        "https://allcity-transport-erp.onrender.com",
        "https://allcity-transport-erp-frontend.onrender.com"
})
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;


    @PostMapping("/add-vehicle")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> addVehicle(
            @Valid @RequestBody VehicleDTO vehicleDTO,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest()
                    .body(new Response(result.getFieldError().getDefaultMessage()));
        }

        return ResponseEntity.ok(vehicleService.addVehicle(vehicleDTO));
    }





    // ================= UPDATE VEHICLE =================
    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateVehicle(
            @PathVariable Long id,
            @RequestBody VehicleDTO dto
    ) {
        return ResponseEntity.ok(vehicleService.updateVehicle(id, dto));
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
            @PathVariable VehicleStatus status
    ) {
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
