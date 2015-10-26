package com.epam.spring.core.booking;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.tickets.Ticket;
import com.epam.spring.core.users.User;

import java.util.Date;
import java.util.List;

public interface BookingService {

    Double getTicketPrice(Event event, Date date, Integer seat, User user);

    // todo: set total price here
    void bookTicket(User user, Ticket ticket);

    List<Ticket> getTicketsForEvent(Event event, Date date);
}
