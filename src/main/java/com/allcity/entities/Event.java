package com.allcity.entities;
import com.allcity.enums.BookingStatus;
import com.allcity.enums.Category;

import com.allcity.enums.VehiclePriority;   // ✔ RIGHT

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "events")
public class Event {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "text")
    private String description;

    @NotNull
    private String eventType; // free text like "Workshop" or "Reminder"

    @Enumerated(EnumType.STRING)
    @Column(name="vehicle_priority",nullable = false)
    private VehiclePriority vehiclePriority;

    @Enumerated(EnumType.STRING)
    @Column(name = "booking_status")
    private BookingStatus bookingStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;

    private String department;

    private LocalDateTime dueDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;


    @Column(name = "is_private_event", nullable = false)
    private Boolean isPrivateEvent = false;

    @Column(name = "is_department_event", nullable = false)
    private Boolean isDepartmentEvent;

    @Column(name = "is_vehicle_update", nullable = false)
    private Boolean isVehicleUpdate;

}
