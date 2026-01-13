package com.allcity.serviceImpl;

import com.allcity.dtos.LeaveApplicationRequest;
import com.allcity.entities.*;
import com.allcity.enums.LeaveStatus;
import com.allcity.repositories.*;
import com.allcity.service.LeaveTypeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Transactional
public class LeaveTypeServiceImpl implements LeaveTypeService {

    private final LeaveApplicationRepository leaveApplicationRepository;
    private final LeaveTypeRepository leaveTypeRepository;
    private final EmployeeRepository employeeRepository;

    public LeaveTypeServiceImpl(
            LeaveApplicationRepository leaveApplicationRepository,
            LeaveTypeRepository leaveTypeRepository,
            EmployeeRepository employeeRepository) {

        this.leaveApplicationRepository = leaveApplicationRepository;
        this.leaveTypeRepository = leaveTypeRepository;
        this.employeeRepository = employeeRepository;
    }

    /* =========================
       LEAVE TYPE METHODS
       ========================= */

    @Override
    public List<LeaveType> getAllLeaveTypes() {
        return leaveTypeRepository.findAll();
    }

    @Override
    public LeaveType createLeaveType(LeaveType leaveType) {
        return leaveTypeRepository.save(leaveType);
    }

    /* =========================
       LEAVE APPLICATION METHODS
       ========================= */

    @Override
    public LeaveApplication applyLeave(LeaveApplicationRequest request) {

        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        LeaveType leaveType = leaveTypeRepository.findById(request.getLeaveTypeId())
                .orElseThrow(() -> new RuntimeException("Leave type not found"));

        long leaveDays = ChronoUnit.DAYS.between(
                request.getStartDate(),
                request.getEndDate()
        ) + 1;

        int usedLeaves = getUsedLeavesForYear(
                employee.getId(),
                leaveType.getId(),
                request.getStartDate().getYear()
        );

        if (usedLeaves + leaveDays > leaveType.getMaxDaysPerYear()) {
            throw new RuntimeException("Insufficient leave balance");
        }

        LeaveApplication application = new LeaveApplication();
        application.setEmployee(employee);
        application.setLeaveType(leaveType);
        application.setStartDate(request.getStartDate());
        application.setEndDate(request.getEndDate());
        application.setReason(request.getReason());
        application.setAppliedDate(LocalDate.now());
        application.setStatus(LeaveStatus.PENDING);
        application.setStatus(LeaveStatus.APPROVED);
        application.setStatus(LeaveStatus.REJECTED);

        return leaveApplicationRepository.save(application);
    }

    @Override
    public LeaveApplication approveLeave(Long applicationId, Long approvedById) {

        LeaveApplication application = leaveApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Leave application not found"));

        Employee approver = employeeRepository.findById(approvedById)
                .orElseThrow(() -> new RuntimeException("Approver not found"));

        application.setStatus(LeaveStatus.APPROVED);
        application.setStatus(LeaveStatus.PENDING);
        application.setStatus(LeaveStatus.REJECTED);
        application.setApprovedBy(approver);
        application.setApprovedDate(LocalDate.now());

        return leaveApplicationRepository.save(application);
    }

    @Override
    public List<LeaveApplication> getAllLeaveApplications() {
        return leaveApplicationRepository.findAll();
    }

    /* =========================
       HELPER METHOD
       ========================= */

    private int getUsedLeavesForYear(Long employeeId, Long leaveTypeId, int year) {

        List<LeaveApplication> applications =
                leaveApplicationRepository.findByEmployeeIdAndStatus(
                        employeeId,
                        LeaveStatus.APPROVED


                );

        return applications.stream()
                .filter(app -> app.getLeaveType().getId().equals(leaveTypeId))
                .filter(app -> app.getStartDate().getYear() == year)
                .mapToInt(app ->
                        (int) ChronoUnit.DAYS.between(
                                app.getStartDate(),
                                app.getEndDate()
                        ) + 1)
                .sum();
    }
}
