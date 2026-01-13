package com.allcity.service;


import com.allcity.dtos.VehiclePurchaseDTO;

import java.util.List;

public interface VehiclePurchaseService {
    VehiclePurchaseDTO save(VehiclePurchaseDTO dto);
    VehiclePurchaseDTO getById(Long id);
    List<VehiclePurchaseDTO> getAll();
    VehiclePurchaseDTO update(Long id, VehiclePurchaseDTO dto);
    void delete(Long id);
}
