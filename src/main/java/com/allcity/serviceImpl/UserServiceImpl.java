package com.allcity.serviceImpl;

import com.allcity.dtos.*;
import com.allcity.entities.User;

import com.allcity.entities.VehicleBooking;
import com.allcity.enums.Role;
import com.allcity.exceptions.EmailAlreadyExistsException;
import com.allcity.exceptions.InvalidCredentialException;
import com.allcity.exceptions.NotFoundException;
import com.allcity.repositories.UserRepository;
import com.allcity.repositories.VehicleBookingRepository;
import com.allcity.security.JwtUtils;
import com.allcity.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final ModelMapper modelMapper;
    private final VehicleBookingRepository vehicleBookingRepository;



    @Override
    public Response registerUser(RegistrationRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return Response.builder()
                    .status(409)
                    .message("User already registered. Please login.")
                    .build();
        }


        User currentUser = null;
        if (SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            try {
                currentUser = getCurrentLoggedInUser();
            } catch (Exception ignored) {}
        }

        // 🔐 ROLE VALIDATION
        if (request.getRole() == Role.ADMIN) {
            // Only existing ADMIN can create ADMIN
            if (currentUser == null || currentUser.getRole() != Role.ADMIN) {
                throw new RuntimeException("Only ADMIN can create another ADMIN");
            }
        }

        if (request.getRole() == Role.MANAGER) {
            if (currentUser == null || currentUser.getRole() != Role.ADMIN) {
                throw new RuntimeException("Only ADMIN can create MANAGER");
            }
        }

        if (request.getRole() == Role.USER) {
            if (request.getDepartmentId() == null) {
                throw new RuntimeException("Department is required for USER");
            }
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .role(request.getRole())
                .isActive(true)
                .build();

        userRepository.save(user);

        return Response.builder()
                .status(200)
                .message("Registration successful")
                .build();

    }






    @Override
    public Response loginUser(LoginRequest loginRequest) {
       User user = userRepository.findByEmail(loginRequest.getEmail())
               .orElseThrow(()-> new NotFoundException("Email Not Found"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialException("Password doesn't match");
        }

        String token = jwtUtils.generateToken(user.getEmail());


        return Response.builder()
                .status(200)
                .message("user logged in successfully")
                .role(user.getRole())
                .token(token)
                .isActive(user.getIsActive())
                .expirationTime("6 months")
                .build();
    }

    @Override
    public Response getAllUsers() {
        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        List<UserDTO> userDTOList = modelMapper.map(users, new TypeToken<List<UserDTO>>(){}.getType());

        return Response.builder()
                .status(200)
                .message("success")
                .users(userDTOList)
                .build();
    }

    @Override
    public Response getOwnAccountDetails() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new NotFoundException("User Not Found"));


        log.info("Inside getOwnAccountDetails user email is {}", email);

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return Response.builder()
                .status(200)
                .message("success")
                .user(userDTO)
                .build();
    }

    @Override
    public User getCurrentLoggedInUser() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByEmail(email)
                .orElseThrow(()-> new NotFoundException("User Not Found"));
    }

    @Override
    public Response updateOwnAccount(UserDTO userDTO) {
       User existingUser = getCurrentLoggedInUser();
       log.info("Inside update user");

        if (userDTO.getEmail() != null) existingUser.setEmail(userDTO.getEmail());
        if (userDTO.getFirstName() != null) existingUser.setFirstName(userDTO.getFirstName());
        if (userDTO.getLastName() != null) existingUser.setLastName(userDTO.getLastName());
        if (userDTO.getPhoneNumber() != null) existingUser.setPhoneNumber(userDTO.getPhoneNumber());

        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
        userRepository.save(existingUser);

        return Response.builder()
                .status(200)
                .message("user updated successfully")
                .build();
    }

    @Override
    public Response deleteOwnAccount() {
        User user = getCurrentLoggedInUser();
        userRepository.delete(user);

        return Response.builder()
                .status(200)
                .message("user deleted successfully")
                .build();
    }

    @Override
    public Response getMyBookingHistory() {

        User user = getCurrentLoggedInUser();

        List<VehicleBooking> bookingList =
                vehicleBookingRepository.findByUserId(user.getId());

        List<VehicleBookingDTO> bookingDTOList =
                modelMapper.map(
                        bookingList,
                        new TypeToken<List<VehicleBookingDTO>>() {}.getType()
                );

        return Response.builder()
                .status(200)
                .message("success")
                .bookings(bookingDTOList)
                .build();
    }




}
