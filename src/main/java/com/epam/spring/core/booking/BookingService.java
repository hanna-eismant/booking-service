package com.epam.spring.core.booking;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.tickets.Ticket;
import com.epam.spring.core.users.User;
import org.joda.time.LocalDateTime;

import java.util.List;

public interface BookingService {

    Double getTicketPrice(Event event, LocalDateTime date, Integer seat, User user);

    /**
     * Assign ticket to user.
     * Inside this method final price (with discount) is calculated and set to ticket.
     */
    void bookTicket(User user, Ticket ticket);

    List<Ticket> getTicketsForEvent(Event event, LocalDateTime date);

    List<Ticket> getFreeTicketsForEvent(Event event, LocalDateTime date);
}
