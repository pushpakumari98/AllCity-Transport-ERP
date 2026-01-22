package com.allcity.entities;

import com.allcity.enums.PermitLevel;
import com.allcity.enums.VehicleStatus;
import com.allcity.enums.VehicleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "tbl_vehicle")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vehicle_id", nullable = false, unique = true, updatable = false)
    private String vehicleId;

    @PrePersist
    public void generateVehicleId() {
        if (vehicleId == null) {
            vehicleId = "VH-" + UUID.randomUUID().toString().substring(0, 8);
        }
        if (vehicleStatus == null) {
            vehicleStatus = VehicleStatus.AVAILABLE;
        }
    }

    @NotBlank(message = "Vehicle registration number is required")
    @Column(nullable = false)
    private String vehicleRegNo;

    @NotNull(message = "Vehicle type is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleType vehicleType;


    @Enumerated(EnumType.STRING)
    private PermitLevel permitLevel;

//    @Min(6000000000L)
//    @Max(9999999999L)
    private Long driverMob;

//    @DecimalMin("0.1")
    private Double price;

//    @Min(1)
    private Integer capacity;

    private String description;
    private String originCity;
    private String destinationCity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleStatus vehicleStatus;


}
