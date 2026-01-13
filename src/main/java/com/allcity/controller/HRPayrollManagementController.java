package com.allcity.controller;

import com.allcity.entities.Employee;
import com.allcity.entities.LeaveApplication;
import com.allcity.entities.LeaveType;
import com.allcity.service.EmployeeService;
import com.allcity.service.LeaveApplicationService;
import com.allcity.service.LeaveTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hr")
@CrossOrigin(origins = "http://localhost:59753")
public class HRPayrollManagementController {

    private final LeaveTypeService leaveTypeService;
    private final LeaveApplicationService leaveApplicationService;
    private final EmployeeService employeeService;

    public HRPayrollManagementController(
            LeaveTypeService leaveTypeService,
            LeaveApplicationService leaveApplicationService,
            EmployeeService employeeService) {

        this.leaveTypeService = leaveTypeService;
        this.leaveApplicationService = leaveApplicationService;
        this.employeeService = employeeService;
    }

    /* =========================
       LEAVE TYPE APIs
       ========================= */

    @GetMapping("/leave-types")
    public ResponseEntity<List<LeaveType>> getAllLeaveTypes() {
        return ResponseEntity.ok(leaveTypeService.getAllLeaveTypes());
    }

    @PostMapping("/leave-types")
    public ResponseEntity<LeaveType> addLeaveType(
            @RequestBody LeaveType leaveType) {
        return ResponseEntity.ok(
                leaveTypeService.createLeaveType(leaveType)
        );
    }

    /* =========================
       LEAVE APPLICATION APIs
       ========================= */

    @GetMapping("/leave-applications")
    public ResponseEntity<List<LeaveApplication>> getAllLeaveApplications() {
        return ResponseEntity.ok(
                leaveApplicationService.getAllApplications()
        );
    }

    @PostMapping("/leave-applications")
    public ResponseEntity<LeaveApplication> applyLeave(
            @RequestBody LeaveApplication application) {
        return ResponseEntity.ok(
                leaveApplicationService.applyLeave(application)
        );
    }

    /* =========================
       EMPLOYEE APIs
       ========================= */

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(
                employeeService.getAllEmployees()
        );
    }
}
