package com.epam.spring.core.events;

import java.util.List;

import org.joda.time.LocalDateTime;

import com.epam.spring.core.auditoriums.Auditorium;
import com.epam.spring.core.shared.exceptions.DuplicateException;
import com.epam.spring.core.shared.exceptions.NotFoundException;
import com.epam.spring.core.tickets.Ticket;

public interface EventService {

    /**
     * Create new event in Booking system.
     *
     * @param name
     *            event name. Cannot be {@code null} or empty. Should be unique.
     * @param basePrice
     *            event base price. Cannot be {@code null} or 0.
     * @param rating
     *            event rating. Cannot be {@code null}.
     * @return saved event.
     * @throws IllegalArgumentException
     *             if any passed parameters are incorrect.
     * @throws DuplicateException
     *             if event with passed name already exist.
     */
    Event create(String name, Double basePrice, Rating rating) throws IllegalArgumentException, DuplicateException;

    /**
     * Find event with specific ID.
     *
     * @param id
     *            event id for search.
     * @return found event.
     * @throws IllegalArgumentException
     *             if event with specific ID doesn't exist.
     * @throws NotFoundException
     *             if passed id is {@code null}.
     */
    Event getById(Long id) throws IllegalArgumentException, NotFoundException;

    /**
     * Find all events in Booking system.
     *
     * @return list of all events or empty list.
     */
    List<Event> getAll();

    List<Ticket> assignAuditorium(Event event, Auditorium auditorium, LocalDateTime date);

    Show getShowById(Long showId) throws NotFoundException;
}
