package com.example.e_commerce.website.dtos;

import com.example.e_commerce.website.enums.Role;
import lombok.Data;

@Data
public class UserProfileResponse {

    private  Long id;
    private String userName;
    private String emailId;
    private String mobileNumber;
    private Role role;
}
