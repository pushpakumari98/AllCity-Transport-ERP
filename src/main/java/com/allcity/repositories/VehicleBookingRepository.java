package com.allcity.repositories;

import com.allcity.entities.VehicleBooking;
import com.allcity.enums.BookingStatus;
import com.allcity.enums.VehicleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface VehicleBookingRepository extends JpaRepository<VehicleBooking, Long> {

    List<VehicleBooking> findByUserId(Long userId) ;

    List<VehicleBooking> findByBookingDateBetween(LocalDate startDate, LocalDate endDate);
    List<VehicleBooking>findByBookingStatus(BookingStatus bookingStatus);  // ✅ matches entity

    long countByBookingStatus(BookingStatus bookingStatus);
}