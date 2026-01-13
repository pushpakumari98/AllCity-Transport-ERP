package com.allcity.service;

import com.allcity.dtos.VehicleBookingDTO;
import com.allcity.entities.VehicleBooking;
import com.allcity.enums.BookingStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VehicleBookingService {

    VehicleBooking addBooking(VehicleBookingDTO dto);

    VehicleBooking updateBooking(Long bookingId, VehicleBooking updatedBooking);

    void deleteBooking(Long bookingId);

    List<VehicleBooking> getAllBookings();

    Optional<VehicleBooking> getBookingById(Long bookingId);

    List<VehicleBooking> getBookingsByStatus(BookingStatus bookingStatus);


    List<VehicleBooking> getBookingsBetweenDates(LocalDate startDate, LocalDate endDate);

    long countPendingBookings();

    long countCompletedBookings();

    void updateStatusBasedOnPod(Long bookingId, String podFilePath);
}
