package com.allcity.serviceImpl;


import com.allcity.entities.Event;
import com.allcity.enums.BookingStatus;
import com.allcity.enums.Category;
import com.allcity.enums.EventType;
import com.allcity.enums.VehiclePriority;
import com.allcity.repositories.EventRepository;
import com.allcity.service.EventService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
    public class EventServiceImpl implements EventService {

        private final EventRepository repo;

        public EventServiceImpl(EventRepository repo) { this.repo = repo; }



        @Override
        public Event updateEvent(Long id, Event e) {
            Event ex = repo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
            // update fields
            ex.setTitle(e.getTitle());
            ex.setCategory(Category.UPCOMING);
            ex.setCategory(Category.IN_PROGRESS);
            ex.setCategory(Category.COMPLETED);
            ex.setCategory(Category.PENDING);
            ex.setCategory(Category.OVERDUE);
            ex.setCategory(Category.CANCELLED);

            // ... other setters
            return repo.save(ex);
        }

    @Override
    public Event createEvent(Event e) {
        return repo.save(e);
    }

    @Override
        public List<Event> getAllEvents() {
            return repo.findAll(); }

    @Override
    public Event getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
    }

    @Override
        public void deleteEvent(Long id) {
            repo.deleteById(id); }

        @Override
        public List<Event> getByDepartment(String department) {
            return repo.findByDepartment(department); }

    @Override
    public List<Event> getByBookingStatus(BookingStatus bookingStatus) {
        return repo.findByBookingStatus(bookingStatus);
    }

    @Override
    public List<Event> getByCategory(String category) {
        try {
            Category categoryEnum = Category.valueOf(category.toUpperCase());
            return repo.findByCategory(categoryEnum);
        } catch (IllegalArgumentException e) {
            return new ArrayList<>();
        }
    }
    @Override
    public List<Event> getByEventType(String eventType) {
        try {
            EventType typeEnum = EventType.valueOf(eventType.toUpperCase());
            return repo.findByEventType(typeEnum);
        } catch (IllegalArgumentException e) {
            return new ArrayList<>();
        }
    }
    @Override
    public List<Event> getByVehiclePriority(String priority) {
        try {
            VehiclePriority priorityEnum =
                    VehiclePriority.valueOf(priority.toUpperCase());

            return repo.findByVehiclePriority(priorityEnum);

        } catch (IllegalArgumentException e) {
            return new ArrayList<>();
        }
    }

}

