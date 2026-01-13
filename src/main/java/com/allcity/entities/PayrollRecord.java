package com.allcity.entities;

import com.allcity.enums.PayrollStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payroll_records")
public class PayrollRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "pay_period_start", nullable = false)
    private LocalDate payPeriodStart;

    @Column(name = "pay_period_end", nullable = false)
    private LocalDate payPeriodEnd;

    @Column(name = "basic_salary", nullable = false)
    private BigDecimal basicSalary;

    @Column(name = "allowances")
    private BigDecimal allowances = BigDecimal.ZERO;

    @Column(name = "deductions")
    private BigDecimal deductions = BigDecimal.ZERO;

    @Column(name = "net_salary", nullable = false)
    private BigDecimal netSalary;

    @Enumerated(EnumType.STRING)
    private PayrollStatus status = PayrollStatus.PENDING;

    @Column(name = "processed_date")
    private LocalDate processedDate;

    public void setEmployee(Employee employee) {
    }

    public void setPayPeriodStart(LocalDate start) {
    }

    public void setPayPeriodEnd(LocalDate end) {
    }

    public void setBasicSalary(BigDecimal basicSalary) {
    }

    public void setAllowances(BigDecimal zero) {
    }

    public void setDeductions(BigDecimal deductions) {
    }

    public void setNetSalary(BigDecimal subtract) {
    }

    // Getters and setters
}

