package com.allcity.serviceImpl;

import com.allcity.entities.Manager;
import com.allcity.repositories.ManagerRepository;
import com.allcity.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    @Override
    public Manager createManager(Manager manager) {
        return managerRepository.save(manager);
    }

    @Override
    public Manager updateManager(Long id, Manager manager) {
        Manager existing = managerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manager not found"));
        // Update fields...
        return managerRepository.save(existing);
    }

    @Override
    public void deleteManager(Long id) {
        managerRepository.deleteById(id);
    }

    @Override
    public Manager getManagerById(Long id) {
        return managerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manager not found"));
    }

    @Override
    public List<Manager> getManagersByDepartment(Long departmentId) {
        return managerRepository.findByDepartmentId(departmentId);
    }
}
