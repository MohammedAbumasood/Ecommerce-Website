package com.example.e_commerce.website.controller;

import com.example.e_commerce.website.dtos.EditProfileRequest;
import com.example.e_commerce.website.dtos.UserLoginRequest;
import com.example.e_commerce.website.dtos.UserProfileResponse;
import com.example.e_commerce.website.dtos.UserRegistrationRequest;
import com.example.e_commerce.website.model.User;
import com.example.e_commerce.website.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
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

    @PostMapping("/login")
    public String loginUser(@Valid @RequestBody UserLoginRequest loginRequest){
        String response = userService.loginUser(loginRequest);
        return response;
    }

    @GetMapping("/user/{id}")
    public UserProfileResponse findUserById(@PathVariable long id){
        UserProfileResponse response = userService.findUserById(id);
        return response;
    }

    @PutMapping("/update/{id}")
    public String updateUserById(@Valid @RequestBody EditProfileRequest request, @PathVariable long id){
        String response = userService.updateUserById(request, id);
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable long id){
        String response = userService.deleteUserById(id);
        return response;
    }
}
