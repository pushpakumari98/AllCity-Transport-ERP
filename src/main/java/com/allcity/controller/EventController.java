package com.allcity.controller;

import com.allcity.entities.Event;
import com.allcity.enums.BookingStatus;
import com.allcity.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = {"http://localhost:59753","https://allcity-transport-erp.onrender.com","https://allcity-transport-erp-frontend.onrender.com"})
@RequiredArgsConstructor
public class EventController {
    private final EventService service;

    // ================= CREATE EVENT (ADMIN ONLY) =================
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Event> create(@RequestBody Event dto) {
        return ResponseEntity.ok(service.createEvent(dto));
    }


    // ================= GET ALL EVENTS =================
    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','USER')")
    public ResponseEntity<List<Event>> all() {
        return ResponseEntity.ok(service.getAllEvents());
    }

    // ================= GET EVENT BY ID =================
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','USER')")
    public ResponseEntity<Event> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // ================= UPDATE EVENT =================
    @PutMapping("/update/{id}")
    @PreAuthorize("""
        hasAuthority('ADMIN') ||
        (hasAuthority('MANAGER') &&
         @securityService.isSameDepartment(authentication, #dto.department))
    """)
    public ResponseEntity<Event> update(
            @PathVariable Long id,
            @RequestBody Event dto) {
        return ResponseEntity.ok(service.updateEvent(id, dto));
    }

    // ================= DELETE EVENT (ADMIN ONLY) =================
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    // ================= FILTERS =================
    @GetMapping("/department/{dept}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity<List<Event>> byDept(@PathVariable("dept") String department) {
        return ResponseEntity.ok(service.getByDepartment(department));
    }

    @GetMapping("/booking-status/{status}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','USER')")
    public ResponseEntity<List<Event>> getByBookingStatus(
            @PathVariable BookingStatus status) {
        return ResponseEntity.ok(service.getByBookingStatus(status));
    }

    @GetMapping("/category/{category}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','USER')")
    public ResponseEntity<List<Event>> getByCategory(@PathVariable String category) {
        return ResponseEntity.ok(service.getByCategory(category));
    }

    @GetMapping("/event-type/{eventType}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','USER')")
    public ResponseEntity<List<Event>> getByEventType(@PathVariable String eventType) {
        return ResponseEntity.ok(service.getByEventType(eventType));
    }

    @GetMapping("/vehicle-priority/{priority}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER','USER')")
    public ResponseEntity<List<Event>> getByVehiclePriority(@PathVariable String priority) {
        return ResponseEntity.ok(service.getByVehiclePriority(priority));
    }
}
