package com.allcity.repositories;

import com.allcity.entities.PayrollRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PayrollRecordRepository extends JpaRepository<PayrollRecord, Long> {

    // Get payroll history of an employee
    List<PayrollRecord> findByEmployeeId(Long employeeId);

    // Get payroll records within a pay period
    List<PayrollRecord> findByPayPeriodStartBetween(
            LocalDate startDate, LocalDate endDate);

    // Check if payroll already generated for a period
    boolean existsByEmployeeIdAndPayPeriodStartAndPayPeriodEnd(
            Long employeeId,
            LocalDate payPeriodStart,
            LocalDate payPeriodEnd
    );
}
