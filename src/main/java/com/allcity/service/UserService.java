package com.allcity.service;

import com.allcity.dtos.LoginRequest;
import com.allcity.dtos.RegistrationRequest;
import com.allcity.dtos.Response;
import com.allcity.dtos.UserDTO;
import com.allcity.entities.User;

public interface UserService {

    Response registerUser(RegistrationRequest registrationRequest);
    Response loginUser(LoginRequest loginRequest);
    Response getAllUsers();
    Response getOwnAccountDetails();
    User getCurrentLoggedInUser();
    Response updateOwnAccount(UserDTO userDTO);
    Response deleteOwnAccount();
    Response getMyBookingHistory();
}
