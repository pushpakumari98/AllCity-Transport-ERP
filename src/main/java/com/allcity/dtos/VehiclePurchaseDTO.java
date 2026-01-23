package com.allcity.dtos;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class VehiclePurchaseDTO {

    private Long id;   // ✅ MATCHES ANGULAR

    private LocalDate date;
    private String vehicleNo;
    private Double bookingHire;
    private LocalDate bookingReceivingBalanceDate;
    private String fromLocation;
    private String toLocation;
    private String transportName;
    private String detain;
    private LocalDate podReceivedDate;
    private LocalDate lorryBalancePaidDate;
}
