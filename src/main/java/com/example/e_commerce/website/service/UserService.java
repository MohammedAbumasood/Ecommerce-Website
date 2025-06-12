package com.example.e_commerce.website.service;

import com.example.e_commerce.website.dtos.EditProfileRequest;
import com.example.e_commerce.website.dtos.UserLoginRequest;
import com.example.e_commerce.website.dtos.UserProfileResponse;
import com.example.e_commerce.website.dtos.UserRegistrationRequest;
import com.example.e_commerce.website.mapper.UserMapper;
import com.example.e_commerce.website.model.User;
import com.example.e_commerce.website.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper,PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }


    public String userRegistration(UserRegistrationRequest request){
        if(userRepository.existsByEmailId(request.getEmailId())){
            throw new IllegalArgumentException("Email already registered");
        }

        User user = userMapper.mapToEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User saved successfully";
    }

    public UserProfileResponse findUserById(long id){
        User user = userRepository.findById(id).
                orElseThrow(() -> new RuntimeException("User not found with id "+id));
        return userMapper.mapToResponseDto(user);
    }

    public String updateUserById(EditProfileRequest request, long id){
        User user = userRepository.findById(id).
                orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.convertToEntity(request, user);
        userRepository.save(user);
        return "User updated successfully";
    }

    public String deleteUserById(long id){
        if(!userRepository.existsById(id)){
            throw  new RuntimeException("User not found with id "+id);
        }
        userRepository.deleteById(id);
        return "User deleted successfully!";
    }

    public String loginUser(UserLoginRequest loginRequest){
        User user = userRepository.findByEmailId(loginRequest.getEmailId()).
                orElseThrow(() -> new RuntimeException("Invalid email or password"));
        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid email or password");
        }
        return "Login successful!";
    }
}
