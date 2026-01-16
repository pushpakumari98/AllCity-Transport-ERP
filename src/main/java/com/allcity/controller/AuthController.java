package com.allcity.controller;
import com.allcity.dtos.LoginRequest;
import com.allcity.dtos.RegistrationRequest;
import com.allcity.dtos.Response;
import com.allcity.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:59753","https://allcity-transport-erp.onrender.com","https://allcity-transport-erp-frontend.onrender.com"})
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Response> registerUser(
            @RequestBody @Valid RegistrationRequest request) {
        return ResponseEntity.ok(userService.registerUser(request));
    }
    @PostMapping("/login")
    public ResponseEntity<Response> loginUser(@RequestBody @Valid LoginRequest request){
        return ResponseEntity.ok(userService.loginUser(request));
    }
}
