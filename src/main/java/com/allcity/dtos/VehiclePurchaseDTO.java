package com.allcity.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class VehiclePurchaseDTO {

    private Long id;   // ✅ MATCHES ANGULAR

    private String vehicleNo;
    private Double bookingHire;

    private String fromLocation;
    private String toLocation;
    private String transportName;
    private String detain;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate bookingReceivingBalanceDate;

   @JsonFormat(pattern = "yyyy-MM-dd")
   private LocalDate podReceivedDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate lorryBalancePaidDate;
}
