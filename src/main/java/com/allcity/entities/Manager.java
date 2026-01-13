package com.allcity.entities;

import com.allcity.enums.ManagerType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "managers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Manager email is required")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    private String firstName;

    private String lastName;

    @NotBlank(message = "Phone Number is required")
    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private ManagerType managerType; // e.g. DEPARTMENT_HEAD, PROJECT_MANAGER, TEAM_LEAD

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "years_of_experience")
    private Integer yearsOfExperience;

    @Column(name = "specialization_area")
    private String specializationArea; // e.g. "Software Development", "HR Management", etc.

    private Boolean isActive;

    private final LocalDateTime createdAt = LocalDateTime.now();

    // One manager can oversee multiple employees
    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("manager")
    private List<Employee> subordinates = new ArrayList<>();


}
