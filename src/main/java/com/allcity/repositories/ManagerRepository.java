package com.allcity.repositories;

import com.allcity.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    List<Manager> findByDepartmentId(Long departmentId);
    List<Manager> findByIsActive(Boolean isActive);
}
