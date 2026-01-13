package com.allcity.service;

import com.allcity.entities.Event;
import com.allcity.enums.BookingStatus;

import java.util.List;

public interface EventService {
    Event createEvent(Event e);
    Event updateEvent(Long id, Event e);
    List<Event> getAllEvents();
    Event getById(Long id);
    void deleteEvent(Long id);
    List<Event> getByDepartment(String department);
    List<Event> getByBookingStatus(BookingStatus status);

    List<Event> getByCategory(String category);
    List<Event> getByEventType(String eventType);
    List<Event> getByVehiclePriority(String priority);

}
