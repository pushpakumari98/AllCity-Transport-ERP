package com.allcity.dtos;

import com.allcity.enums.PermitLevel;
import com.allcity.enums.VehicleStatus;
import com.allcity.enums.VehicleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleDTO {


    private PermitLevel permitLevel;
    private Long driverMob;
    private Double price;
    private Integer capacity;
    private String description;
    private String originCity;
    private String destinationCity;
    // VehicleDTO
    private String vehicleRegNo;
    private VehicleType vehicleType;





}
