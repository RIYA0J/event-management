package com.example.event_management.eventmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.event_management.eventmanagement.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    // Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    void save(Optional<User> user);
}
