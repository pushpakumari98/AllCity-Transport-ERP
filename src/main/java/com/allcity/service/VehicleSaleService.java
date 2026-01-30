package com.allcity.service;

import com.allcity.dtos.VehicleSaleDTO;
import com.allcity.entities.VehicleSale;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
@Service
public interface VehicleSaleService {



    public VehicleSale addVehicleSale(VehicleSaleDTO dto, MultipartFile document) throws IOException;


    /*
     * Add a new vehicle sale
     */

    VehicleSale updateVehicleSale(Long id, VehicleSale dto);

    VehicleSale getVehicleSaleById(Long id);

    List<VehicleSale> getAllVehicleSales();

    List<VehicleSale> filterVehicleSales(
            LocalDate startDate,
            LocalDate endDate,
            String paymentMode,
            String status
    );

    void deleteVehicleSale(Long id);

    VehicleSale uploadSaleDocument(Long id, MultipartFile file) throws IOException;

    List<VehicleSale> getSalesByFilter(String filter);
}
