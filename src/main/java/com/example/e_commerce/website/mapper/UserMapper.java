package com.example.e_commerce.website.mapper;

import com.example.e_commerce.website.dtos.EditProfileRequest;
import com.example.e_commerce.website.dtos.UserProfileResponse;
import com.example.e_commerce.website.dtos.UserRegistrationRequest;
import com.example.e_commerce.website.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapToEntity(UserRegistrationRequest dto){

        User user = new User();
        user.setUserName(dto.getUserName());
        user.setEmailId(dto.getEmailId());
        user.setMobileNumber(dto.getMobileNumber());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        return user;
    }

    public UserProfileResponse mapToResponseDto(User user){
        UserProfileResponse response = new UserProfileResponse();
        response.setId(user.getId());
        response.setUserName(user.getUserName());
        response.setEmailId(user.getEmailId());
        response.setMobileNumber(user.getMobileNumber());
        response.setRole(user.getRole());
        return response;
    }

    public void convertToEntity(EditProfileRequest request, User user){
        user.setUserName(request.getUserName());
        user.setEmailId(request.getEmailId());
        user.setMobileNumber(request.getMobileNumber());
    }
}
