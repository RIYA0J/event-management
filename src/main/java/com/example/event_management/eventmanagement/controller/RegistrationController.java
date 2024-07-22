package com.example.event_management.eventmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.event_management.eventmanagement.model.Event;
import com.example.event_management.eventmanagement.model.User;
import com.example.event_management.eventmanagement.service.EventService;
import com.example.event_management.eventmanagement.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/events")
public class RegistrationController {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @PostMapping("/{id}/register")
    public ResponseEntity<?> registerForEvent(@PathVariable Long id, Principal principal) {
        Event event = eventService.getEventById(id);
        if (event == null) {
            return ResponseEntity.badRequest().body("Event not found");
        }
        User user = userService.findByUsername(principal.getName());
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
        user.getRegisteredEvents().add(event);
        event.getAttendees().add(user);
        userService.save(user);
        return ResponseEntity.ok("Registered successfully");
    }

    @GetMapping("/{id}/attendees")
    public ResponseEntity<Set<User>> getEventAttendees(@PathVariable Long id) {
        Event event = eventService.getEventById(id);
        if (event == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(event.getAttendees());
    }
}
