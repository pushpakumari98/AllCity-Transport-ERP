package com.allcity.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_driver")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "serial_no", unique = true, updatable = false)
    private Long serialNo;

    @Column(name = "vehicle_no", nullable = false)
    private String vehicleNo;

    @Column(name = "driver_name", nullable = false)
    private String driverName;

    @Column(name = "contact_number", nullable = false)
    private String contactNumber;

    @Column(name = "started_from", nullable = false)
    private String startedFrom;

    @Column(name = "destination", nullable = false)
    private String destination;

    @Column(name = "carry_material_type", nullable = false)
    private String carryMaterialType;

    @Column(name = "date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(name = "address", nullable = false)
    private String address;
}
