package com.allcity.entities;

import com.allcity.enums.PermitLevel;
import com.allcity.enums.VehicleStatus;
import com.allcity.enums.VehicleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tbl_vehicle")
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Auto-generated Vehicle ID (Business ID)
    @Column(name = "vehicle_id", nullable = false, unique = true, updatable = false)
    private String vehicleId;

    @PrePersist
    public void generateVehicleId() {
        if (this.vehicleId == null) {
            this.vehicleId = "VH-" + UUID.randomUUID().toString().substring(0, 8);
        }
    }


    @Enumerated(EnumType.STRING)
    private PermitLevel permitLevel;

    @Min(value = 6000000000L, message = "Invalid mobile number")
    @Max(value = 9999999999L, message = "Invalid mobile number")
    @Column(unique = true)
    private Long driverMob;

    @NotBlank(message = "Vehicle registration number is required")
    @Column(nullable = false)
    private String vehicleRegNo;

    @NotNull(message = "Vehicle type is required")
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;


    @DecimalMin(value = "0.1", message = "Price One Way is required")
    private Double price;

    @Min(value = 1, message = "Capacity required")
    private Integer capacity;

    private String description;

    private String originCity;

    private String destinationCity;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_status", nullable = false)
    private VehicleStatus vehicleStatus;


    @Lob
    @Column(name = "image", columnDefinition = "bytea")
    private byte[] image;

}
