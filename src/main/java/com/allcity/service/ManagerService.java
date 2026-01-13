package com.allcity.service;

import com.allcity.entities.Manager;
import java.util.List;

public interface ManagerService {
    List<Manager> getAllManagers();
    Manager createManager(Manager manager);
    Manager updateManager(Long id, Manager manager);
    void deleteManager(Long id);
    Manager getManagerById(Long id);
    List<Manager> getManagersByDepartment(Long departmentId);
}
