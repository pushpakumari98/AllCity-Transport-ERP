package com.allcity.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_driver")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ Auto-generated, immutable
    @Column(name = "serial_no", nullable = false, unique = true, updatable = false)
    private Long serialNo;

    // ✅ Numeric vehicle number ONLY
    @Column(name = "vehicle_no", nullable = false)
    @NotBlank(message = "Vehicle number is required")
    @Pattern(
            regexp = "^[A-Z]{2}[0-9]{1,2}[A-Z]{1,2}[0-9]{4}$"
    )
    private String vehicleNo;

    @Column(name = "driver_name")
    private String driverName;

    @Column(name = "contact_number")
    private String contactNumber;

    @NotBlank(message = "vehicle starting place is required")
    private String startedFrom;

    @NotBlank(message = "destination")
    private String destination;

    @NotBlank(message = "material type is required")
    private String carryMaterialType;

    @NotBlank(message = "date is required")
    private LocalDate date;

    @NotBlank(message = "address is required")
    private String address;

    // getters and setters
}
