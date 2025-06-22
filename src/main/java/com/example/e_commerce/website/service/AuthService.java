package com.example.e_commerce.website.service;

import com.example.e_commerce.website.dtos.AuthRequest;
import com.example.e_commerce.website.dtos.AuthResponse;
import com.example.e_commerce.website.model.User;
import com.example.e_commerce.website.repository.UserRepository;
import com.example.e_commerce.website.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserDetails user = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtUtil.generateToken(user);

        User users = userRepository.findByEmailId(request.getEmail())
                .orElseThrow(() -> new RuntimeException("user Not found"));

        return new AuthResponse(token,
                users.getUserName(),
                users.getRole().toString(),
                users.getId());
    }
}
