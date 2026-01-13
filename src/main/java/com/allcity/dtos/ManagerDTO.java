package com.allcity.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagerDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String managerType;
    private Long departmentId;
    private String departmentName;
    private Integer yearsOfExperience;
    private String specializationArea;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private Integer subordinatesCount;
}
