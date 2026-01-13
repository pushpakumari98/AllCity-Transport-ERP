package com.allcity.repositories;

import com.allcity.entities.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    int countByEmployeeIdAndDateBetweenAndStatus(
            Long employeeId,
            LocalDate startDate,
            LocalDate endDate,
            String status
    );
}
