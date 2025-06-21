package com.example.e_commerce.website.controller;

import com.example.e_commerce.website.dtos.AuthRequest;
import com.example.e_commerce.website.dtos.AuthResponse;
import com.example.e_commerce.website.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody AuthRequest request){
        System.out.println("Login Request" + request);
        return authService.login(request);
    }
}
