package com.example.event_management.eventmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.event_management.eventmanagement.model.Event;
import com.example.event_management.eventmanagement.repository.EventRepository;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    // Retrieve all events
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Retrieve a specific event by its ID
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
    }

    // Create a new event
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    // Update an existing event
    public Event updateEvent(Long id, Event eventDetails) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));

        event.setName(eventDetails.getName());
        event.setDescription(eventDetails.getDescription());
        event.setDate(eventDetails.getDate());
        event.setLocation(eventDetails.getLocation());
        event.setCategory(eventDetails.getCategory());
        event.setImageUrl(eventDetails.getImageUrl());

        return eventRepository.save(event);
    }

    // Delete an event
    public void deleteEvent(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
        eventRepository.delete(event);
    }
}
