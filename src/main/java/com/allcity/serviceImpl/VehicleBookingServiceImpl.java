package com.allcity.serviceImpl;


import com.allcity.dtos.VehicleBookingDTO;
import com.allcity.entities.Vehicle;
import com.allcity.entities.VehicleBooking;
import com.allcity.enums.BookingStatus;
import com.allcity.enums.VehicleStatus;
import com.allcity.repositories.VehicleBookingRepository;
import com.allcity.repositories.VehicleRepository;
import com.allcity.service.VehicleBookingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleBookingServiceImpl implements VehicleBookingService {

    @Autowired
    private VehicleBookingRepository vehicleBookingRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    @Transactional
    public VehicleBooking addBooking(VehicleBookingDTO dto) {

        Vehicle vehicle = vehicleRepository.findById(dto.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        // 🚫 Block booking if vehicle is not AVAILABLE
        if (vehicle.getVehicleStatus() != VehicleStatus.AVAILABLE) {
            throw new RuntimeException("Vehicle is not available for booking");
        }

        VehicleBooking booking = VehicleBooking.builder()
                .vehicle(vehicle)
                .vehicleNo(vehicle.getVehicleRegNo()) // auto from vehicle
                .startedFrom(dto.getStartedFrom())
                .destination(dto.getDestination())
                .vehicleType(vehicle.getVehicleType())
                .driverName(dto.getDriverName())
                .bookingHire(dto.getBookingHire())
                .bookingAdvance(dto.getBookingAdvance())
                .bookingBalance(dto.getBookingBalance())
                .bookingDate(LocalDate.now())
                .bookingStatus(BookingStatus.PENDING)
                .build();

        // 🔒 Mark vehicle as BOOKED
        vehicle.setVehicleStatus(VehicleStatus.COMPLETED);
        vehicleRepository.save(vehicle);

        return vehicleBookingRepository.save(booking);
    }


    @Override
    public VehicleBooking updateBooking(Long id, VehicleBooking updatedBooking) {

        return vehicleBookingRepository.findById(id)
                .map(existing -> {

                    // Update vehicle reference
                    if (updatedBooking.getVehicle() != null) {
                        Vehicle vehicle = vehicleRepository.findById(
                                updatedBooking.getVehicle().getId()
                        ).orElseThrow(() ->
                                new RuntimeException("Vehicle not found"));
                        existing.setVehicle(vehicle);
                    }


                    return vehicleBookingRepository.save(existing);
                })
                .orElseThrow(() ->
                        new RuntimeException("Booking not found with ID: " + id));
    }

    @Override
    public void deleteBooking(Long id) {

        VehicleBooking booking = vehicleBookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        Vehicle vehicle = booking.getVehicle();
        vehicle.setVehicleStatus(VehicleStatus.AVAILABLE);

        vehicleRepository.save(vehicle);
        vehicleBookingRepository.deleteById(id);
    }


    @Override
    public List<VehicleBooking> getAllBookings() {
        return vehicleBookingRepository.findAll();
    }

    @Override
    public Optional<VehicleBooking> getBookingById(Long id) {
        return vehicleBookingRepository.findById(id);
    }

    @Override
    public List<VehicleBooking> getBookingsByStatus(BookingStatus status) {
        return vehicleBookingRepository.findByBookingStatus(status);
    }

    @Override
    public List<VehicleBooking> getBookingsBetweenDates(LocalDate start, LocalDate end) {
        return vehicleBookingRepository.findByBookingDateBetween(start, end);
    }

    @Override
    public long countPendingBookings() {
        return vehicleBookingRepository.countByBookingStatus(BookingStatus.PENDING);
    }

    @Override
    public long countCompletedBookings() {
        return vehicleBookingRepository.countByBookingStatus(BookingStatus.COMPLETED);
    }

    @Transactional
    @Override
    public void updateStatusBasedOnPod(Long id, String podFilePath) {

        VehicleBooking booking = vehicleBookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setPodDocument(podFilePath);
        booking.setBookingStatus(BookingStatus.COMPLETED);

        // 🔓 Make vehicle available again
        Vehicle vehicle = booking.getVehicle();
        vehicle.setVehicleStatus(VehicleStatus.AVAILABLE);

        vehicleRepository.save(vehicle);
    }

}
