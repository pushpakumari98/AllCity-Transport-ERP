package com.allcity.controller;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.allcity.dtos.EmployeeDTO;
import com.allcity.dtos.ManagerDTO;
import com.allcity.entities.Manager;
import com.allcity.enums.EmployeeStatus;
import com.allcity.enums.ManagerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.allcity.entities.Department;
import com.allcity.entities.Employee;
import com.allcity.service.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:59753")
public class AdminController {

    @Autowired
    private final AdminService service;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ManagerService managerService;




    public AdminController(AdminService service) {
        this.service = service;
    }
    private EmployeeDTO convertToDTO(Employee employee) {

        EmployeeDTO dto = new EmployeeDTO();

        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setContactNo(employee.getContactNo());
        dto.setDesignation(employee.getDesignation());
        dto.setRole(employee.getRole());
        dto.setStatus(employee.getStatus());
        dto.setBasicSalary(employee.getBasicSalary());

        if (employee.getDepartment() != null) {
            dto.setDepartmentId(employee.getDepartment().getId());
            dto.setDepartmentName(employee.getDepartment().getName());
        }

        return dto;
    }



    // Employee endpoints
    @GetMapping("/employees")
    public List<EmployeeDTO> allEmployees() {
        return service.listEmployees().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return service.getEmployee(id);
    }



    @PostMapping("/employees")
    public EmployeeDTO createEmployee(@RequestBody Map<String, Object> payload) {
        Employee employee = new Employee();
        employee.setName((String) payload.get("name"));
        employee.setContactNo((String) payload.get("contactNo"));
        employee.setEmail((String) payload.get("email"));
        employee.setDesignation((String) payload.get("designation"));
        employee.setRole((String) payload.get("role"));

        // Set defaults for required fields
        employee.setStatus(EmployeeStatus.ACTIVE);
        employee.setBasicSalary(BigDecimal.valueOf(30000)); // Default salary

        // Handle department
        Map<String, Object> deptMap = (Map<String, Object>) payload.get("department");
        if (deptMap != null && deptMap.get("id") != null) {
            Long deptId = Long.valueOf(deptMap.get("id").toString());
            Department dept = departmentService.getDepartmentById(deptId);
            employee.setDepartment(dept);
        }

        Employee saved = service.createEmployee(employee);
        return convertToDTO(saved);
    }




    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee e) {
        return service.updateEmployee(id, e);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
    }

    // Department endpoints
    @GetMapping("/departments")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @PostMapping("/departments")
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.createDepartment(department);
    }




    // Manager endpoints
    @GetMapping("/managers")
    public List<ManagerDTO> getAllManagers() {
        return managerService.getAllManagers().stream()
                .map(this::convertToManagerDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/managers")
    public ManagerDTO createManager(@RequestBody Map<String, Object> payload) {
        Manager manager = new Manager();
        manager.setEmail((String) payload.get("email"));
        manager.setPassword((String) payload.get("password"));
        manager.setFirstName((String) payload.get("firstName"));
        manager.setLastName((String) payload.get("lastName"));
        manager.setPhoneNumber((String) payload.get("phoneNumber"));
        manager.setManagerType(ManagerType.valueOf((String) payload.get("managerType")));
        manager.setYearsOfExperience(payload.get("yearsOfExperience") != null ?
                Integer.valueOf(payload.get("yearsOfExperience").toString()) : null);
        manager.setSpecializationArea((String) payload.get("specializationArea"));
        manager.setIsActive(payload.get("isActive") != null ? (Boolean) payload.get("isActive") : true);

        // Handle department
        if (payload.get("departmentId") != null) {
            Long deptId = Long.valueOf(payload.get("departmentId").toString());
            Department dept = departmentService.getDepartmentById(deptId);
            manager.setDepartment(dept);
        }

        Manager saved = managerService.createManager(manager);
        return convertToManagerDTO(saved);
    }

    private ManagerDTO convertToManagerDTO(Manager manager) {
        ManagerDTO dto = new ManagerDTO();
        dto.setId(manager.getId());
        dto.setEmail(manager.getEmail());
        dto.setFirstName(manager.getFirstName());
        dto.setLastName(manager.getLastName());
        dto.setPhoneNumber(manager.getPhoneNumber());
        dto.setManagerType(manager.getManagerType().toString());
        dto.setDepartmentId(manager.getDepartment() != null ? manager.getDepartment().getId() : null);
        dto.setDepartmentName(manager.getDepartment() != null ? manager.getDepartment().getName() : null);
        dto.setYearsOfExperience(manager.getYearsOfExperience());
        dto.setSpecializationArea(manager.getSpecializationArea());
        dto.setIsActive(manager.getIsActive());
        dto.setCreatedAt(manager.getCreatedAt());
        dto.setSubordinatesCount(manager.getSubordinates() != null ? manager.getSubordinates().size() : 0);
        return dto;
    }



}