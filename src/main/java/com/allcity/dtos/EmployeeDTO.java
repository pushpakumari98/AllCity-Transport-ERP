package com.allcity.dtos;

import com.allcity.enums.EmployeeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String name;
    private String contactNo;
    private String email;
    private String designation;
    private Long departmentId;

    // Optional fields with defaults
    private LocalDate dateOfJoining;
    private String role;
    private EmployeeStatus status; // Default status
    private BigDecimal basicSalary = BigDecimal.ZERO; // Default salary

    public void setDepartmentName(String name) {
    }
}
