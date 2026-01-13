package com.allcity.repositories;


import com.allcity.entities.LeaveApplication;
import com.allcity.enums.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Long> {

        List<LeaveApplication> findByEmployeeId(Long employeeId);
        List<LeaveApplication> findByStatus(LeaveStatus status);
        List<LeaveApplication> findByEmployeeIdAndStatus(Long employeeId, LeaveStatus status);
    }


