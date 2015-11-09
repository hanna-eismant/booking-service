package com.epam.spring.core.tickets.dao;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.tickets.Ticket;
import com.epam.spring.core.users.User;

import java.util.List;

public interface TicketDAO {

    Ticket create(Ticket ticket);

    Ticket update(Ticket ticket);

    List<Ticket> findByEvent(Event event);

    List<User> findByUser(User user);
}
