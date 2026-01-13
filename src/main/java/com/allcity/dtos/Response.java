package com.allcity.dtos;

import com.allcity.enums.Role;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    //generic
    private int status;
    private String message;

    //for login
    private String token;
    private Role role;
    private Boolean isActive;
    private String expirationTime;

    //user data output
    private UserDTO user;
    private List<UserDTO> users;

    //Booking data output
    //private BookingDTO booking;
   // private List<BookingDTO> bookings;

    //Room data output
   // private RoomDTO room;
    private List<VehicleDTO> vehicleDTOS;

    //Payment data output
   // private PaymentDTO payment;
   // private List<PaymentDTO> payments;

    //Payment data output
   // private NotificationDTO notification;
   // private List<NotificationDTO> notifications;

    private final LocalDateTime timestamp = LocalDateTime.now();

    private List<VehicleBookingDTO> bookings;


    public Response(String success, String userRegisteredSuccessfully) {
    }

    public Response(String vehicleUpdatedSuccessfully) {
    }
}
