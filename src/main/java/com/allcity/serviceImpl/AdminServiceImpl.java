package com.allcity.serviceImpl;

import com.allcity.entities.Employee;
import com.allcity.entities.Department;

import com.allcity.repositories.DepartmentRepository;
import com.allcity.repositories.EmployeeRepository;
import com.allcity.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final EmployeeRepository employeeRepo;

    @Autowired
    private final DepartmentRepository deptRepo;

    public AdminServiceImpl(EmployeeRepository employeeRepo, DepartmentRepository deptRepo) {
        this.employeeRepo = employeeRepo;
        this.deptRepo = deptRepo;
    }

    // Employee methods
    @Override
    public List<Employee> listEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee getEmployee(Long id) {
        return employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
    }

    @Override
    public Employee createEmployee(Employee e) {
        if (e.getDepartment() != null && e.getDepartment().getId() != null) {
            Department dept = deptRepo.findById(e.getDepartment().getId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));
            e.setDepartment(dept);
        }

        return employeeRepo.save(e);
    }

    @Override
    public Employee updateEmployee(Long id, Employee e) {
        Employee existing = employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        existing.setName(e.getName());
        existing.setContactNo(e.getContactNo());
        existing.setEmail(e.getEmail());
        existing.setDesignation(e.getDesignation());

        if (e.getDepartment() != null && e.getDepartment().getId() != null) {
            Department dept = deptRepo.findById(e.getDepartment().getId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));
            existing.setDepartment(dept);
        }

        return employeeRepo.save(existing);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepo.deleteById(id);
    }

    // Department methods
    @Override
    public List<Department> listDepartments() {
        return deptRepo.findAll();
    }

    @Override
    public Department createDepartment(Department d) {
        return deptRepo.save(d);
    }
}
