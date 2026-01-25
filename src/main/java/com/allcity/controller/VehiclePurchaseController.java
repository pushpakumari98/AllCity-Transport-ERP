package com.allcity.controller;

import com.allcity.dtos.VehiclePurchaseDTO;
import com.allcity.entities.VehiclePurchase;
import com.allcity.service.VehiclePurchaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle-purchases")
@CrossOrigin(origins = {"http://localhost:59753","https://allcity-transport-erp.onrender.com","https://allcity-transport-erp-frontend.onrender.com"}) // Angular support
public class VehiclePurchaseController {

    private final VehiclePurchaseService vehiclePurchaseService;

    @Autowired
    public VehiclePurchaseController(VehiclePurchaseService vehiclePurchaseService) {
        this.vehiclePurchaseService = vehiclePurchaseService;
    }


    // ✅ Create Vehicle Purchase
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<VehiclePurchaseDTO> create(
            @Valid @RequestBody VehiclePurchaseDTO dto)
    {
        VehiclePurchaseDTO saved = vehiclePurchaseService.save(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // ✅ Get by Sl. No (ID)
    @GetMapping("/{id}")
    public ResponseEntity<VehiclePurchaseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(vehiclePurchaseService.getById(id));
    }




    // ✅ Get All Records
    @GetMapping
    public ResponseEntity<List<VehiclePurchaseDTO>> getAll() {
        return ResponseEntity.ok(vehiclePurchaseService.getAll());
    }


    // ✅ UPDATE
    @PutMapping("{id}")
    public ResponseEntity<VehiclePurchaseDTO> updatePurchase(
            @PathVariable Long id,
            @RequestBody VehiclePurchaseDTO dto) {

        return ResponseEntity.ok(vehiclePurchaseService.update(id, dto));
    }


    // ✅ Delete Record
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchase(@PathVariable Long id) {
        vehiclePurchaseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
