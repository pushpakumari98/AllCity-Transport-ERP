package com.allcity.service;

import com.allcity.entities.Employee;
import com.allcity.entities.Department;
import java.util.List;

public interface AdminService {
    // Employee operations
    List<Employee> listEmployees();
    Employee getEmployee(Long id);
    Employee createEmployee(Employee e);
    Employee updateEmployee(Long id, Employee e);
    void deleteEmployee(Long id);

    // Department operations
    List<Department> listDepartments();
    Department createDepartment(Department d);
}
