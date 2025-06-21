package com.example.e_commerce.website.controller;

import com.example.e_commerce.website.dtos.EditProfileRequest;
import com.example.e_commerce.website.dtos.UserProfileResponse;
import com.example.e_commerce.website.dtos.UserRegistrationRequest;
import com.example.e_commerce.website.model.User;
import com.example.e_commerce.website.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public String userRegistration(@Valid @RequestBody UserRegistrationRequest request){
        String response = userService.userRegistration(request); 
        return response;
    }


    @PreAuthorize("hasAnyRole('SELLER', 'CUSTOMER')")
    @GetMapping("/user/{id}")
    public UserProfileResponse findUserById(@PathVariable long id){
        UserProfileResponse response = userService.findUserById(id);
        return response;
    }

    @PreAuthorize("hasAnyRole('CUSTOMER', 'SELLER')")
    @PutMapping("/update/{id}")
    public String updateUserById(@Valid @RequestBody EditProfileRequest request, @PathVariable long id){
        String response = userService.updateUserById(request, id);
        return response;
    }

    @PreAuthorize("hasAnyRole('CUSTOMER', 'SELLER')")
    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable long id){
        String response = userService.deleteUserById(id);
        return response;
    }
}
