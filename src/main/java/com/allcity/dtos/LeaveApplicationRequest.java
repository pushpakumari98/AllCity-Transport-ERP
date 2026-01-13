package com.allcity.dtos;


import lombok.Data;

import java.time.LocalDate;
@Data
public class LeaveApplicationRequest {
        private Long employeeId;
        private Long leaveTypeId;
        private LocalDate startDate;
        private LocalDate endDate;
        private String reason;


    // Getters and setters
    }


