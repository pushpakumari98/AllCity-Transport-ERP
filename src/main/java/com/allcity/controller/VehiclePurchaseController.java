package com.allcity.controller;

import com.allcity.dtos.VehiclePurchaseDTO;
import com.allcity.service.VehiclePurchaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle-purchases")
@CrossOrigin(origins = {"http://localhost:59753","https://allcity-transport-erp.onrender.com"}) // Angular support
public class VehiclePurchaseController {

    private final VehiclePurchaseService service;

    public VehiclePurchaseController(VehiclePurchaseService service) {
        this.service = service;
    }

    // ✅ Create Vehicle Purchase
    @PostMapping
    public ResponseEntity<VehiclePurchaseDTO> create(
            @RequestBody VehiclePurchaseDTO dto) {
        VehiclePurchaseDTO saved = service.save(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // ✅ Get by Sl. No (ID)
    @GetMapping("/{id}")
    public ResponseEntity<VehiclePurchaseDTO> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // ✅ Get All Records
    @GetMapping
    public ResponseEntity<List<VehiclePurchaseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // ✅ Update Record
    @PutMapping("/{id}")
    public ResponseEntity<VehiclePurchaseDTO> update(
            @PathVariable Long id,
            @RequestBody VehiclePurchaseDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    // ✅ Delete Record
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Vehicle purchase deleted successfully");
    }
}
