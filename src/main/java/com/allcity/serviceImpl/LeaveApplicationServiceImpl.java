package com.allcity.serviceImpl;

import com.allcity.entities.LeaveApplication;

import com.allcity.enums.LeaveStatus;
import com.allcity.repositories.LeaveApplicationRepository;
import com.allcity.service.LeaveApplicationService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class LeaveApplicationServiceImpl implements LeaveApplicationService {

    private final LeaveApplicationRepository leaveApplicationRepository;

    public LeaveApplicationServiceImpl(
            LeaveApplicationRepository leaveApplicationRepository) {
        this.leaveApplicationRepository = leaveApplicationRepository;
    }

    @Override
    public List<LeaveApplication> getAllApplications() {
        return leaveApplicationRepository.findAll();
    }

    @Override
    public LeaveApplication applyLeave(LeaveApplication application) {
        application.setStatus(LeaveStatus.PENDING);
        application.setStatus(LeaveStatus.APPROVED);
        application.setStatus(LeaveStatus.REJECTED);
        application.setAppliedDate(LocalDate.now());
        return leaveApplicationRepository.save(application);
    }
}
