package com.example.e_commerce.website.dtos;

import com.example.e_commerce.website.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String token;
    private String userName;
    private String role;
    private Long userId;
}
