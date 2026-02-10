package com.allcity.entities;

import com.allcity.enums.PaymentMode;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
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

    /**
     * Auto-generated Vehicle ID
     * Example: VH-A3F9C2D1
     */
    @Column(name = "vehicle_id", nullable = false, unique = true, updatable = false)
    private String vehicleId;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "lorry_number", nullable = false)
    private String lorryNumber;

    @NotNull
    @Column(nullable = false)
    private Double weight;

    @NotNull
    @Column(name = "lorry_hire", nullable = false)
    private BigDecimal lorryHire;

    @Column
    private BigDecimal commission;

    @Column
    private Integer bility;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_mode", nullable = false)
    private PaymentMode paymentMode;

    @Column(name = "petrol_pump")
    private String petrolPump;

    @Column(name = "total_advance")
    private Integer totalAdvance;

    /**
     * Auto-generate values before saving
     */
    @PrePersist
    public void prePersist() {

        // Auto set date
        if (this.date == null) {
            this.date = LocalDate.now();
        }

        // Auto generate vehicleId
        if (this.vehicleId == null || this.vehicleId.isBlank()) {
            this.vehicleId = "VH-" + UUID.randomUUID()
                    .toString()
                    .replace("-", "")
                    .substring(0, 8)
                    .toUpperCase();
        }
    }
}
