package com.allcity.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DriverDTO {


    @NotNull(message = "Date cannot be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotBlank(message = "vehicle_No is required")
    private String vehicleNo;

    @NotBlank(message = "vehicle starting place is required")
    private String startedFrom;

    @NotBlank(message = "destination is required")
    private String destination;

    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$")
    private String contactNumber;

    @NotBlank(message = "address is required")
    private String address;

    @NotBlank(message ="driver name is required")
    private String driverName;

    @NotBlank(message = "material type is required")
    private String carryMaterialType;

}