package com.example.e_commerce.website.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginRequest {

    @Email(message = "Invalid email format")
    private String emailId;

    @NotBlank(message = "password is required ")
    private String password;
}
