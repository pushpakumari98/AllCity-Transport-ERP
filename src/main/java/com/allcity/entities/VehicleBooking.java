package com.allcity.entities;

import com.allcity.enums.BookingStatus;
import com.allcity.enums.VehicleStatus;
import com.allcity.enums.VehicleType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tbl_booking")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Auto-generated Booking ID
    @Column(name = "booking_id", nullable = false, unique = true, updatable = false)
    private String bookingId;

    @PrePersist
    public void generateBookingId() {
        if (this.bookingId == null) {
            this.bookingId = "BK-" + UUID.randomUUID().toString().substring(0, 8);
        }
    }

    // 🔗 LINK TO VEHICLE (Foreign Key handled by JPA)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Vehicle vehicle;

    @Column(name="vehicleNo",nullable = false)
    @Pattern(
            regexp = "^[A-Z]{2}[- ]?[0-9]{1,2}[- ]?[A-Z]{1,2}[- ]?[0-9]{4}$",
            message = "Invalid vehicle number format"
    )
    @Size(min = 9, max = 13, message = "Vehicle number must be 9 to 13 characters")
    private String vehicleNo;

    @Column(name = "started_from", nullable = false)
    private String startedFrom;

    @Column(nullable = false)
    private String destination;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleType vehicleType;

    @Column(name = "driver_name")
    private String driverName;

    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal bookingHire;

    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal bookingBalance;

    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal bookingAdvance;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate bookingDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate bookingReceivedDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate lorryBalancePaidDate;

    private String detain;

    private Boolean podReceived;

    private String podDocument;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus bookingStatus;
}
