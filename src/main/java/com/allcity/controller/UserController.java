package com.allcity.controller;

import com.allcity.dtos.LoginRequest;
import com.allcity.dtos.RegistrationRequest;
import com.allcity.dtos.Response;
import com.allcity.dtos.UserDTO;

import com.allcity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = {"http://localhost:59753","https://allcity-transport-erp.onrender.com","https://allcity-transport-erp-frontend.onrender.com"})
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity<Response> registerUser(@RequestBody RegistrationRequest registrationRequest){

        Response result = userService.registerUser(registrationRequest);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


    @PostMapping("/user/login")
    public ResponseEntity<Response> loginUser(@RequestBody LoginRequest loginRequest) {
        Response result = userService.loginUser(loginRequest);
        HttpStatus status = result.getStatus() == 200 ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return new ResponseEntity<>(result, status);
    }


    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }


    @PutMapping("/update")
    public ResponseEntity<Response> updateOwnAccount(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.updateOwnAccount(userDTO));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Response> deleteOwnAccount(){
        return ResponseEntity.ok(userService.deleteOwnAccount());
    }

    @GetMapping("/account")
    public ResponseEntity<Response> getOwnAccountDetails(){
        return ResponseEntity.ok(userService.getOwnAccountDetails());
    }


    @GetMapping("/bookings")
    public ResponseEntity<Response> getMyBookingHistory(){
        return ResponseEntity.ok(userService.getMyBookingHistory());
    }
}
