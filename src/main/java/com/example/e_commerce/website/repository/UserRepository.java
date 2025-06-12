package com.example.e_commerce.website.repository;

import com.example.e_commerce.website.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmailId(String emailId);

    Optional<User> findByEmailId(String emailId);
}
