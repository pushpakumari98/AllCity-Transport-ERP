package com.allcity.service;

import java.time.LocalDate;

public interface AttendanceService {

    int getActualWorkingDays(Long employeeId, LocalDate startDate, LocalDate endDate);
}
