package com.allcity.service;


import com.allcity.dtos.VehiclePurchaseDTO;
import com.allcity.entities.VehiclePurchase;

import java.util.List;

public interface VehiclePurchaseService {

    VehiclePurchaseDTO save(VehiclePurchaseDTO dto);
    VehiclePurchaseDTO getById(Long id);
    List<VehiclePurchaseDTO> getAll();

    // ✅ Update

    VehiclePurchaseDTO update(Long id, VehiclePurchaseDTO dto);


    void deleteById(Long id);


}
