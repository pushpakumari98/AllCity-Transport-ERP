package com.allcity.serviceImpl;

import com.allcity.repositories.AttendanceRepository;
import com.allcity.service.AttendanceService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Transactional
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public int getActualWorkingDays(Long employeeId,
                                    LocalDate startDate,
                                    LocalDate endDate) {

        return attendanceRepository.countByEmployeeIdAndDateBetweenAndStatus(
                employeeId,
                startDate,
                endDate,
                "PRESENT"
        );
    }
}
