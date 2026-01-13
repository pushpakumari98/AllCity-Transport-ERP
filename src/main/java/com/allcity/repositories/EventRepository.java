package com.allcity.repositories;

import com.allcity.enums.BookingStatus;

import com.allcity.entities.Event;
import com.allcity.enums.Category;
import com.allcity.enums.EventType;
import com.allcity.enums.VehiclePriority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
     List<Event> findByCategory(Category catEnum) ;


     List<Event> findByEventType(EventType typeEnum) ;


    List<Event> findByVehiclePriority(VehiclePriority priorityEnum);


    List<Event> findByIsPrivateEventTrue() ;

     List<Event> findByIsDepartmentEventTrue();

     List<Event> findByIsVehicleUpdateTrue() ;

    List<Event> findByDepartment(String department);
    List<Event> findByBookingStatus(BookingStatus bookingStatus);
    // add other queries as needed
}
