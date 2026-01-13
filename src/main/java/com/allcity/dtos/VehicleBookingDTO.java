package com.allcity.dtos;

import com.allcity.enums.BookingStatus;
import com.allcity.enums.VehicleStatus;
import com.allcity.enums.VehicleType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleBookingDTO {

    private Long id;



    @NotNull(message = "Vehicle ID is required")
    @JsonProperty("vehicleId")
    private Long vehicleId;

    @NotNull(message = "Date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotBlank(message = "vehicleNo is required")
    public String vehicleNo;

    @NotBlank(message = "Started From is required")
    private String startedFrom;

    @NotBlank(message = "Destination is required")
    private String destination;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleType vehicleType;

    @NotBlank(message = "Driver Name is required")
    private String driverName;

    @DecimalMin(value = "0.0", inclusive = false, message = "Booking hire must be greater than 0")
    private BigDecimal bookingHire;

    @DecimalMin(value = "0.0", inclusive = true, message = "Booking balance cannot be negative")
    private BigDecimal bookingBalance;

    @DecimalMin(value = "0.0", inclusive = true, message = "Booking advance cannot be negative")
    private BigDecimal bookingAdvance;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate bookingReceivedDate;

    private String detain;

    private Boolean podReceived;

    // This will be used for file uploads (e.g., proof of delivery document)
    private MultipartFile podDocument;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lorryBalancePaidDate;



    @Enumerated(EnumType.STRING)

    @Column(nullable = false)
    private BookingStatus bookingStatus;

    @Column(nullable = false)
    private LocalDate bookingDate;



}
