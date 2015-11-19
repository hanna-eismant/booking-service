package com.epam.spring.core.tickets.dao;

import com.epam.spring.core.BaseDAO;
import com.epam.spring.core.events.Event;
import com.epam.spring.core.tickets.Ticket;
import com.epam.spring.core.users.User;

import java.util.List;

public interface TicketDAO extends BaseDAO<Ticket>{

    Ticket update(Ticket ticket);

    List<Ticket> findByEvent(Event event);

    List<User> findByUser(User user);
}
