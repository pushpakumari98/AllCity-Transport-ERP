package com.allcity.entities;

import com.allcity.enums.EmployeeStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Employee name is required")
    private String name;

    private String contactNo;

    @Email(message = "Invalid email format")
    private String email;

    @Column(name = "date_of_joining")
    private LocalDate dateOfJoining;

    private String designation;

    private String role = "USER"; // Default role

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "qualification_document")
    private String qualificationDocument;

    @Enumerated(EnumType.STRING)
    private EmployeeStatus status ;// Default status

    @Column(name = "basic_salary", nullable = false)
    private BigDecimal basicSalary = BigDecimal.ZERO; // Default salary

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;



}
