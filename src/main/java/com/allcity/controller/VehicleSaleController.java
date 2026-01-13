package com.allcity.controller;

import com.allcity.entities.VehicleSale;
import com.allcity.service.VehicleSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:59753") // You can restrict this if needed
public class VehicleSaleController {

    @Autowired
    private VehicleSaleService vehicleSaleService;

    /**
     * Add new Vehicle Sale
     */
    @PostMapping("/vehicle-sales")
    public ResponseEntity<VehicleSale> addVehicleSale(
            @RequestBody VehicleSale dto,
            @RequestParam(value = "document", required = false) MultipartFile document
    ) throws IOException {
        VehicleSale savedSale = vehicleSaleService.addVehicleSale(dto, document);
        return ResponseEntity.ok(savedSale);
    }

    /**
     * Update Vehicle Sale by ID
     */
    @PutMapping("/{id}")
    public ResponseEntity<VehicleSale> updateVehicleSale(
            @PathVariable Long id,
            @RequestBody VehicleSale dto
    ) {
        VehicleSale updatedSale = vehicleSaleService.updateVehicleSale(id, dto);
        return ResponseEntity.ok(updatedSale);
    }

    /**
     * Get Vehicle Sale by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<VehicleSale> getVehicleSaleById(@PathVariable Long id) {
        VehicleSale sale = vehicleSaleService.getVehicleSaleById(id);
        return ResponseEntity.ok(sale);
    }

    /**
     * Get All Vehicle Sales
     */
    @GetMapping("/getallsoldvehicle")
    public ResponseEntity<List<VehicleSale>> getAllVehicleSales() {
        List<VehicleSale> sales = vehicleSaleService.getAllVehicleSales();
        return ResponseEntity.ok(sales);
    }

    /**
     * Filter Vehicle Sales by Date Range, Payment Mode, and Status
     */
    @GetMapping("/filter")
    public ResponseEntity<List<VehicleSale>> filterVehicleSales(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            @RequestParam(required = false) String paymentMode,
            @RequestParam(required = false) String status
    ) {
        List<VehicleSale> filteredSales = vehicleSaleService.filterVehicleSales(startDate, endDate, paymentMode, status);
        return ResponseEntity.ok(filteredSales);
    }

    /**
     * Delete Vehicle Sale by ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicleSale(@PathVariable Long id) {
        vehicleSaleService.deleteVehicleSale(id);
        return ResponseEntity.ok("Vehicle Sale with ID " + id + " deleted successfully.");
    }

    /**
     * Upload Sale Document
     */
    @PostMapping("/{id}/upload")
    public ResponseEntity<VehicleSale> uploadSaleDocument(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        VehicleSale updatedSale = vehicleSaleService.uploadSaleDocument(id, file);
        return ResponseEntity.ok(updatedSale);
    }
}
