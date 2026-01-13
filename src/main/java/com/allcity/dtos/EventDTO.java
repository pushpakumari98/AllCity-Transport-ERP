package com.allcity.dtos;

import com.allcity.enums.BookingStatus;
import com.allcity.enums.Category;
import com.allcity.enums.VehiclePriority;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventDTO {

    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    private String eventType;

    private Category category;
    private VehiclePriority vehiclePriority;
    private BookingStatus bookingStatus;

    private String department;

    private LocalDateTime dueDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private boolean isPrivate;
    private boolean isDepartmentEvent;
    private boolean isVehicleUpdate;

}
