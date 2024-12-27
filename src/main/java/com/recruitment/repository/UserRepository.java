package com.recruitment.repository;

import com.recruitment.model.Employee;
import com.recruitment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    // Find users by their status (e.g., pending or approved)
    List<User> findByStatus(String status);

    // Find user by username
    Optional<User> findByUsername(String username);
    // Find user by email (if you have an email field in your User model)
    Optional<User> findByEmail( String email);
    // Add method to find by email
//
}
