package com.allcity.serviceImpl;

import com.allcity.entities.Employee;
import com.allcity.entities.PayrollRecord;
import com.allcity.repositories.EmployeeRepository;
import com.allcity.repositories.PayrollRecordRepository;
import com.allcity.service.AttendanceService;
import com.allcity.service.PayrollService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
@Transactional
public class PayrollServiceImpl implements PayrollService {

    private final EmployeeRepository employeeRepository;
    private final PayrollRecordRepository payrollRecordRepository;
    private final AttendanceService attendanceService;

    public PayrollServiceImpl(EmployeeRepository employeeRepository,
                              PayrollRecordRepository payrollRecordRepository,
                              AttendanceService attendanceService) {
        this.employeeRepository = employeeRepository;
        this.payrollRecordRepository = payrollRecordRepository;
        this.attendanceService = attendanceService;
    }

    @Override
    public PayrollRecord generatePayroll(Long employeeId, LocalDate startDate, LocalDate endDate) {

        // 1️⃣ Fetch Employee
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + employeeId));

        // 2️⃣ Get actual working days from Attendance module
        int workingDays = attendanceService.getActualWorkingDays(employeeId, startDate, endDate);

        // 3️⃣ Calculate daily salary (assuming 30 days/month)
        BigDecimal dailySalary = employee.getBasicSalary()
                .divide(BigDecimal.valueOf(30), 2, RoundingMode.HALF_UP);

        // 4️⃣ Calculate earned salary
        BigDecimal earnedSalary = dailySalary.multiply(BigDecimal.valueOf(workingDays));

        // 5️⃣ Calculate deductions
        BigDecimal deductions = calculateDeductions(earnedSalary);

        // 6️⃣ Create payroll record
        PayrollRecord payroll = new PayrollRecord();
        payroll.setEmployee(employee);
        payroll.setPayPeriodStart(startDate);
        payroll.setPayPeriodEnd(endDate);
        payroll.setBasicSalary(employee.getBasicSalary());
        payroll.setAllowances(BigDecimal.ZERO);
        payroll.setDeductions(deductions);
        payroll.setNetSalary(earnedSalary.subtract(deductions));

        // 7️⃣ Save & return
        return payrollRecordRepository.save(payroll);
    }

    /* ================= PRIVATE METHODS ================= */

    private BigDecimal calculateDeductions(BigDecimal salary) {
        BigDecimal tax = salary.multiply(BigDecimal.valueOf(0.10)); // 10% tax
        BigDecimal pf = salary.multiply(BigDecimal.valueOf(0.05));  // 5% PF
        return tax.add(pf);
    }
}
