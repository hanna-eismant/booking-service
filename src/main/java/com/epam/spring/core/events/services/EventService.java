package com.epam.spring.core.events.services;

import com.epam.spring.core.auditoriums.Auditorium;
import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.Rating;
import com.epam.spring.core.tickets.Ticket;
import org.joda.time.LocalDateTime;

import java.util.List;

public interface EventService {
    Event create(String name, Double basePrice, Rating rating);

    void remove(Event event);

    Event getById(Long id);

    List<Event> getAll();

    List<Ticket> assignAuditorium(Event event, Auditorium auditorium, LocalDateTime date);
}
