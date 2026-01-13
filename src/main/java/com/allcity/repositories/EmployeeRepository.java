package com.allcity.repositories;
import com.allcity.entities.Employee;
import com.allcity.enums.EmployeeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


        List<Employee> findByDepartmentId(Long departmentId);
        List<Employee> findByStatus( EmployeeStatus status);
        Optional<Employee> findByEmail(String email);
    }


