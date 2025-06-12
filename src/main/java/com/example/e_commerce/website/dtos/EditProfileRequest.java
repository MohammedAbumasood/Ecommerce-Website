package com.example.e_commerce.website.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class EditProfileRequest {

    private String userName;

    @Email(message = "Invalid email format")
    private String emailId;

    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digit")
    private  String mobileNumber;
}
