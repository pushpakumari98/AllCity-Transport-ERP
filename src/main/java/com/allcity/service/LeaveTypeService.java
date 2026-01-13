package com.allcity.service;

import com.allcity.dtos.LeaveApplicationRequest;
import com.allcity.entities.LeaveApplication;
import com.allcity.entities.LeaveType;
import java.util.List;

public interface LeaveTypeService {

    List<LeaveType> getAllLeaveTypes();

    LeaveType createLeaveType(LeaveType leaveType);

    LeaveApplication applyLeave(LeaveApplicationRequest request);

    LeaveApplication approveLeave(Long applicationId, Long approvedById);

    List<LeaveApplication> getAllLeaveApplications();
}
