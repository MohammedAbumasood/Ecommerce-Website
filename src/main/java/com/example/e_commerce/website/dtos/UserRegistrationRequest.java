package com.example.e_commerce.website.dtos;

import com.example.e_commerce.website.enums.Role;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRegistrationRequest {

    @NotBlank(message = "User name is required")
    private String userName;

    @Email
    @NotBlank
    private String emailId;

    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number")
    private String mobileNumber;

    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotNull(message = "Role is Required")
    private Role role;
}

