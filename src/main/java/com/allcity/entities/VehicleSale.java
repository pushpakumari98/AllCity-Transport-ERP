package com.allcity.entities;

import com.allcity.enums.PaymentMode;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tbl_vehicle_sale")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vehicle_id", nullable = false, unique = true, updatable = false)
    private String vehicleId;
    @PrePersist
    public void generateVehicleId() {
        if (this.vehicleId == null) {
            this.vehicleId = "VH-" + UUID.randomUUID()
                    .toString()
                    .substring(0, 8)
                    .toUpperCase();
        }
    }

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(name = "lorry_number", nullable = false)
    private String lorryNumber;

    // weight in whole number
    @Column
    private Integer weight;

    // lorry hire in whole number
    @Column(name = "lorry_hire")
    private Integer lorryHire;

    // commission in whole number
    @Column
    private Integer commission;

    // bility in whole number
    @Column(name="bility")
    private Integer bility;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_mode", nullable = false)
    private PaymentMode paymentMode;

    @Column(name = "petrol_pump")
    private String petrolPump;

    // total advance in whole number
    @Column(name = "total_advance")
    private Integer totalAdvance;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "vehicle_ref_id", nullable = false)
//    private Vehicle vehicle;
}
