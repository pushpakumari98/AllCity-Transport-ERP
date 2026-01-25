package com.allcity.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDate;


@Entity
@Data
@Table(name = "vehicle_purchases")
public class VehiclePurchase {

    // Sl. No (Auto-generated numeric)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sl_no")
    private Long id;

    // Date
    @Column(name = "entry_date")
    private LocalDate date;


    @Column(name="vehicle_no")
    @Pattern(
            regexp = "^[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{4}$",
            message = "Vehicle number must be like MH12AB1234"
    )
    @Size(min = 9, max = 13, message = "Vehicle number must be 9 to 13 characters")
    private String vehicleNo;



    // Booking Hire
    @Column(name = "booking_hire")
    private Double bookingHire;


    // Booking Receiving Balance Date
    @Column(name = "booking_receiving_balance_date")
    private LocalDate bookingReceivingBalanceDate;

    // From Location
    @Column(name = "from_location")
    private String fromLocation;

    // To Location
    @Column(name = "to_location")
    private String toLocation;

    // Transport Name
    @Column(name = "transport")
    private String transportName;

    // Detain Amount
    @Column(name = "detain")
    private Double detain;


    // POD Received Date
    @Column(name = "pod_received_date")
    private LocalDate podReceivedDate;

    // Lorry Balance Paid Date
    @Column(name = "lorry_balance_paid_date")
    private LocalDate lorryBalancePaidDate;
}
