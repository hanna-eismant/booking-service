package com.epam.spring.core.events.services;

import com.epam.spring.core.auditoriums.Auditorium;
import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.Rating;

import java.util.Date;
import java.util.List;

public interface EventService {
    Event create(String name, Integer basePrice, Rating rating);

    void remove(Event event);

    Event getById(Long id);

    List<Event> getAll();

    void assignAuditorium(Event event, Auditorium auditorium, Date date);
}
