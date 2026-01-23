package com.allcity.serviceImpl;

import com.allcity.dtos.VehiclePurchaseDTO;
import com.allcity.entities.VehiclePurchase;
import com.allcity.repositories.VehiclePurchaseRepository;

import com.allcity.service.VehiclePurchaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehiclePurchaseServiceImpl implements VehiclePurchaseService {

    private final VehiclePurchaseRepository repo;

    public VehiclePurchaseServiceImpl(VehiclePurchaseRepository repo) {
        this.repo = repo;
    }

    // ✅ Save
    @Override
    public VehiclePurchaseDTO save(VehiclePurchaseDTO dto) {

        VehiclePurchase entity = new VehiclePurchase();

        entity.setDate(dto.getDate());
        entity.setVehicleNo(dto.getVehicleNo());
        entity.setBookingHire(dto.getBookingHire());
        entity.setBookingReceivingBalanceDate(dto.getBookingReceivingBalanceDate());
        entity.setFromLocation(dto.getFromLocation());
        entity.setToLocation(dto.getToLocation());
        entity.setTransportName(dto.getTransportName());
        entity.setDetain(dto.getDetain());
        entity.setPodReceivedDate(dto.getPodReceivedDate());
        entity.setLorryBalancePaidDate(dto.getLorryBalancePaidDate());

        VehiclePurchase saved = repo.save(entity);
        return convertToDTO(saved);
    }

    // ✅ Get By ID
    @Override
    public VehiclePurchaseDTO getById(Long id) {
        VehiclePurchase entity = repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Vehicle Purchase not found with ID: " + id));

        return convertToDTO(entity);
    }

    // ✅ Get All
    @Override
    public List<VehiclePurchaseDTO> getAll() {
        return repo.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    // ✅ Update
    @Override
    public VehiclePurchaseDTO update(Long id, VehiclePurchaseDTO dto) {

        VehiclePurchase entity = repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Vehicle Purchase not found with ID: " + id));

        entity.setDate(dto.getDate());
        entity.setVehicleNo(dto.getVehicleNo());
        entity.setBookingHire(dto.getBookingHire());
        entity.setBookingReceivingBalanceDate(dto.getBookingReceivingBalanceDate());
        entity.setFromLocation(dto.getFromLocation());
        entity.setToLocation(dto.getToLocation());
        entity.setTransportName(dto.getTransportName());
        entity.setDetain(dto.getDetain());
        entity.setPodReceivedDate(dto.getPodReceivedDate());
        entity.setLorryBalancePaidDate(dto.getLorryBalancePaidDate());

        return convertToDTO(repo.save(entity));
    }




    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }



    // 🔁 Entity → DTO
    private VehiclePurchaseDTO convertToDTO(VehiclePurchase entity) {

        VehiclePurchaseDTO dto = new VehiclePurchaseDTO();

        dto.setId(entity.getId());
        dto.setDate(entity.getDate());
        dto.setVehicleNo(entity.getVehicleNo());
        dto.setBookingHire(entity.getBookingHire());
        dto.setBookingReceivingBalanceDate(entity.getBookingReceivingBalanceDate());
        dto.setFromLocation(entity.getFromLocation());
        dto.setToLocation(entity.getToLocation());
        dto.setTransportName(entity.getTransportName());
        dto.setDetain(entity.getDetain());
        dto.setPodReceivedDate(entity.getPodReceivedDate());
        dto.setLorryBalancePaidDate(entity.getLorryBalancePaidDate());

        return dto;
    }
}
