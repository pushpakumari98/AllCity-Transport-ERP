package com.allcity.dtos;

import com.allcity.enums.PaymentMode;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleSaleDTO {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long vehicleRefId;

    @NotBlank
    private String vehicleId;

    @NotNull(message = "Date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotBlank(message = "Lorry number is required")
    private String lorryNumber;

    @DecimalMin(value = "0.0", inclusive = false, message = "Weight must be greater than 0")
    private Double weight;


    @DecimalMin(value = "0.0", inclusive = false, message = "Lorry hire must be greater than 0")
    private BigDecimal lorryHire;

    @DecimalMin(value = "0.0", inclusive = true, message = "Commission cannot be negative")
    private BigDecimal commission;

    @DecimalMin(value = "0", inclusive = true, message = "Bility cannot be negative")
    private Integer bility;

    @NotNull(message = "Payment mode is required")
    private PaymentMode paymentMode; // Enum: UPI, IMPS

    private String petrolPump;

    @DecimalMin(value = "0.0", inclusive = true, message = "Total advance cannot be negative")
    private BigDecimal totalAdvance;
}
