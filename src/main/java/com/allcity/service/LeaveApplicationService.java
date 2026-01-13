package com.allcity.service;

import com.allcity.entities.LeaveApplication;
import java.util.List;

public interface LeaveApplicationService {

    List<LeaveApplication> getAllApplications();

    LeaveApplication applyLeave(LeaveApplication application);
}
