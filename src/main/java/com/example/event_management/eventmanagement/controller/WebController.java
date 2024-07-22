package com.example.event_management.eventmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.event_management.eventmanagement.model.Event;
import com.example.event_management.eventmanagement.service.EventService;

@Controller
public class WebController {

    @Autowired
    private EventService eventService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "home";
    }

    @GetMapping("/events/{id}")
    public String eventDetails(@PathVariable Long id, Model model) {
        Event event = eventService.getEventById(id);
        model.addAttribute("event", event);
        return "event-details";
    }

    @GetMapping("/events/new")
    public String createEventForm(Model model) {
        model.addAttribute("event", new Event());
        return "create-event";
    }

    @PostMapping("/events")
    public String createEvent(@ModelAttribute Event event) {
        eventService.createEvent(event);
        return "redirect:/";
    }

    @GetMapping("/events/{id}/edit")
    public String editEventForm(@PathVariable Long id, Model model) {
        Event event = eventService.getEventById(id);
        model.addAttribute("event", event);
        return "edit-event";
    }

    @PostMapping("/events/{id}")
    public String updateEvent(@PathVariable Long id, @ModelAttribute Event eventDetails) {
        eventService.updateEvent(id, eventDetails);
        return "redirect:/events/" + id;
    }

    @PostMapping("/events/{id}/delete")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return "redirect:/";
    }
}
