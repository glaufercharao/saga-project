package com.sagaproject.order.core.controller;

import com.sagaproject.order.core.document.Event;
import com.sagaproject.order.core.dtos.EventFilter;
import com.sagaproject.order.core.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/event")
public class EventController {

    private final EventService service;

    @GetMapping
    public Event findByFilters(EventFilter filters) {
        return service.findByFilters(filters);
    }

    @GetMapping("/all")
    public List<Event> findAll() {
        return service.findAll();
    }
}