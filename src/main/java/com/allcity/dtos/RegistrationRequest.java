package com.allcity.dtos;

import com.allcity.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

  //  @NotBlank(message = "FirstName is required")
    private String firstName;

   // @NotBlank(message = "LastName is required")
    private String lastName;

   // @NotBlank(message = "Email is required")
    private String email;

   // @NotBlank(message = "LastName is required")
    private String phoneNumber;

   // @NotBlank(message = "Password is required")
    private String password;

    private Role role;

    // only required for USER & MANAGER
    private Long departmentId;
}
