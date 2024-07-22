package com.example.event_management.eventmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.event_management.eventmanagement.model.Erole;
import com.example.event_management.eventmanagement.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(Erole name);
}
