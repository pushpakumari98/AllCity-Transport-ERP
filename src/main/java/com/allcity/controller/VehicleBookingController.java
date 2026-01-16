package com.allcity.controller;

import com.allcity.dtos.VehicleBookingDTO;
import com.allcity.entities.VehicleBooking;
import com.allcity.enums.BookingStatus;
import com.allcity.service.VehicleBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:59753","https://allcity-transport-erp.onrender.com","https://allcity-transport-erp-frontend.onrender.com"})
@RestController
@RequestMapping("/api/bookings")
public class VehicleBookingController {

    @Autowired
    private VehicleBookingService vehicleBookingService;
    public VehicleBookingController(VehicleBookingService vehicleBookingService) {
        this.vehicleBookingService = vehicleBookingService;
    }

    // ✅ Create new booking
    @PostMapping("/bookvehicle")
    public ResponseEntity<VehicleBooking> addBooking(
            @RequestBody VehicleBookingDTO dto) {
        return ResponseEntity.ok(vehicleBookingService.addBooking(dto));
    }

    // ✅ Update booking by ID
    @PutMapping("/{id}")
    public ResponseEntity<VehicleBooking> updateBooking(
            @PathVariable Long id,
            @RequestBody VehicleBooking updatedBooking) {

        VehicleBooking booking = vehicleBookingService.updateBooking(id, updatedBooking);
        return ResponseEntity.ok(booking);
    }


    // ✅ Delete booking by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id) {
        vehicleBookingService.deleteBooking(id);
        return ResponseEntity.ok("Booking deleted successfully!");
    }

    // ✅ Get all bookings
    @GetMapping("/all/bookings")
    public ResponseEntity<List<VehicleBooking>> getAllBookings() {
        List<VehicleBooking> bookings = vehicleBookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    // ✅ Get booking by ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<VehicleBooking>> getBookingById(@PathVariable Long id) {
        Optional<VehicleBooking> booking = vehicleBookingService.getBookingById(id);
        return ResponseEntity.ok(booking);
    }

    // ✅ Get bookings by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<VehicleBooking>> getBookingsByStatus(@PathVariable("status") BookingStatus bookingStatus) {
        List<VehicleBooking> bookings = vehicleBookingService.getBookingsByStatus(bookingStatus);
        return ResponseEntity.ok(bookings);
    }

    // ✅ Get bookings between dates
    @GetMapping("/dates")
    public ResponseEntity<List<VehicleBooking>> getBookingsBetweenDates(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<VehicleBooking> bookings = vehicleBookingService.getBookingsBetweenDates(startDate, endDate);
        return ResponseEntity.ok(bookings);
    }

    // ✅ Count pending bookings
    @GetMapping("/count/pending")
    public ResponseEntity<Long> countPendingBookings() {
        return ResponseEntity.ok(vehicleBookingService.countPendingBookings());
    }

    // ✅ Count completed bookings
    @GetMapping("/count/completed")
    public ResponseEntity<Long> countCompletedBookings() {
        return ResponseEntity.ok(vehicleBookingService.countCompletedBookings());
    }

    // ✅ Update POD file path (proof of delivery)
    @PutMapping("/{id}/pod")
    public ResponseEntity<String> updatePod(
            @PathVariable Long id,
            @RequestParam String podFilePath) {
        vehicleBookingService.updateStatusBasedOnPod(id, podFilePath);
        return ResponseEntity.ok("POD updated successfully!");
    }
}
