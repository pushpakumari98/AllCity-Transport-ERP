package com.allcity.entities;

import com.allcity.enums.LeaveTypeStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "leave_types")
public class LeaveType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int maxDaysPerYear;

    private String description;

    @Enumerated(EnumType.STRING)
    private LeaveTypeStatus status;



    // getters and setters
}
