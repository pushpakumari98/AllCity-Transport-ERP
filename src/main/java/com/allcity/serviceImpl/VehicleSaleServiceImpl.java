package com.allcity.serviceImpl;
import com.allcity.entities.VehicleSale;
import com.allcity.enums.PaymentMode;
import com.allcity.repositories.VehicleSaleRepository;
import com.allcity.service.VehicleSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class VehicleSaleServiceImpl implements VehicleSaleService {

    @Autowired
    private VehicleSaleRepository vehicleSaleRepository;

    /*
     * Add a new vehicle sale
     */
    @Override
    public VehicleSale addVehicleSale(VehicleSale dto, MultipartFile document) throws IOException {

        // Validate and get vehicle reference

        // Convert DTO → Entity
        VehicleSale sale = VehicleSale.builder()
                .vehicleId(dto.getVehicleId())
                .date(dto.getDate() != null ? dto.getDate() : LocalDate.now())
                .lorryNumber(dto.getLorryNumber())
                .weight(dto.getWeight())
                .lorryHire(dto.getLorryHire())
                .commission(dto.getCommission())
                .bility(dto.getBility())
                .paymentMode(dto.getPaymentMode())
                .petrolPump(dto.getPetrolPump())
                .totalAdvance(dto.getTotalAdvance())
                .build();

        // If a document file is provided, you can store its name/path (optional)
        if (document != null && !document.isEmpty()) {
            String fileName = document.getOriginalFilename();
            // TODO: Save file to disk or database
            // Example: Files.copy(document.getInputStream(), Paths.get("uploads/" + fileName));
        }

        return vehicleSaleRepository.save(sale);
    }


    /**
     * Update existing vehicle sale
     */
    @Override
    public VehicleSale updateVehicleSale(Long id, VehicleSale dto) {
        VehicleSale existing = vehicleSaleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle sale not found with ID: " + id));

        existing.setVehicleId(dto.getVehicleId());
        existing.setDate(dto.getDate());
        existing.setLorryNumber(dto.getLorryNumber());
        existing.setWeight(dto.getWeight());
        existing.setLorryHire(dto.getLorryHire());
        existing.setCommission(dto.getCommission());
        existing.setBility(dto.getBility());
        existing.setPaymentMode(dto.getPaymentMode());
        existing.setPetrolPump(dto.getPetrolPump());
        existing.setTotalAdvance(dto.getTotalAdvance());

        return vehicleSaleRepository.save(existing);
    }


    /**
     * Get vehicle sale by ID
     */
    @Override
    public VehicleSale getVehicleSaleById(Long id) {
        return vehicleSaleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle sale not found with ID: " + id));
    }

    /**
     * Get all vehicle sales
     */
    @Override
    public List<VehicleSale> getAllVehicleSales() {
        return vehicleSaleRepository.findAll();
    }

    /**
     * Filter sales by date, payment mode, and optional status
     */
    @Override
    public List<VehicleSale> filterVehicleSales(LocalDate startDate, LocalDate endDate, String paymentMode, String status) {
        // Example placeholder — implement your custom filter query in repository
        // For now, this returns all records
        if (paymentMode != null) {
            try {
                PaymentMode mode = PaymentMode.valueOf(paymentMode.toUpperCase());
                // Example (requires a method in repository like findByDateBetweenAndPaymentMode):
                // return vehicleSaleRepository.findByDateBetweenAndPaymentMode(startDate, endDate, mode);
            } catch (IllegalArgumentException ex) {
                throw new RuntimeException("Invalid payment mode: " + paymentMode);
            }
        }
        return vehicleSaleRepository.findAll();
    }

    /**
     * Delete vehicle sale by ID
     */
    @Override
    public void deleteVehicleSale(Long id) {
        if (!vehicleSaleRepository.existsById(id)) {
            throw new RuntimeException("Vehicle sale not found with ID: " + id);
        }
        vehicleSaleRepository.deleteById(id);
    }

    /**
     * Upload sale document (optional)
     */
    @Override
    public VehicleSale uploadSaleDocument(Long id, MultipartFile file) throws IOException {
        VehicleSale sale = vehicleSaleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle sale not found with ID: " + id));

        if (file != null && !file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            // TODO: Save file to your desired location
        }
        return vehicleSaleRepository.save(sale);
    }
}
