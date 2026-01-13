package com.allcity.service;

import com.allcity.entities.PayrollRecord;

import java.time.LocalDate;

public interface PayrollService {

    /**
     * Generate payroll for a specific employee
     * between a given date range.
     *
     * @param employeeId Employee ID
     * @param startDate  Payroll period start date
     * @param endDate    Payroll period end date
     * @return PayrollRecord
     */
    PayrollRecord generatePayroll(Long employeeId, LocalDate startDate, LocalDate endDate);
}
