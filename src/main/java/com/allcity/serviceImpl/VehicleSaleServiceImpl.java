package com.allcity.serviceImpl;
import com.allcity.dtos.VehicleSaleDTO;
import com.allcity.entities.VehicleSale;
import com.allcity.enums.PaymentMode;
import com.allcity.repositories.VehicleSaleRepository;
import com.allcity.service.VehicleSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
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
    public VehicleSale addVehicleSale(VehicleSaleDTO dto, MultipartFile document) throws IOException {

        VehicleSale sale = VehicleSale.builder()

                .date(dto.getDate() != null ? dto.getDate() : LocalDate.now())
// ensures date is today if null
                .lorryNumber(dto.getLorryNumber())
                .weight(dto.getWeight() != null ? dto.getWeight() : 0.0)
                .lorryHire(dto.getLorryHire() != null ? dto.getLorryHire() : BigDecimal.ZERO)
                .commission(dto.getCommission() != null ? dto.getCommission() : BigDecimal.ZERO)
                .bility(dto.getBility() != null ? dto.getBility().intValue() : 0)
                .paymentMode(dto.getPaymentMode())
                .petrolPump(dto.getPetrolPump())
                .totalAdvance(dto.getTotalAdvance() != null ? dto.getTotalAdvance().intValue() : 0)
                .build();

        // Save document if exists
        if (document != null && !document.isEmpty()) {
            String fileName = document.getOriginalFilename();
            // TODO: save file
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


    @Override
    public List<VehicleSale> getSalesByFilter(String filter) {

        LocalDate today = LocalDate.now();

        return switch (filter.toLowerCase()) {

            case "today" -> vehicleSaleRepository.findByDate(today);  // exact today

            case "week" -> {
                LocalDate start = today.with(java.time.DayOfWeek.MONDAY);
                LocalDate end = today.with(java.time.DayOfWeek.SUNDAY);
                yield vehicleSaleRepository.findByDateBetween(start, end);
            }

            case "month" -> {
                LocalDate start = today.withDayOfMonth(1);
                LocalDate end = today.withDayOfMonth(today.lengthOfMonth());
                yield vehicleSaleRepository.findByDateBetween(start, end);
            }

            case "year" -> {
                LocalDate start = today.withDayOfYear(1);
                LocalDate end = today.withDayOfYear(today.lengthOfYear());
                yield vehicleSaleRepository.findByDateBetween(start, end);
            }

            default -> vehicleSaleRepository.findAll();
        };
    }


}
