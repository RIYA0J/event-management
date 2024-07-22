package com.example.event_management.eventmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.event_management.eventmanagement.model.Event;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByCategory(String category);
    List<Event> findByLocation(String location);
    List<Event> findByOrganizerId(Long organizerId);
}
