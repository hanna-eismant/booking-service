package com.epam.spring.core.tickets.dao;

import com.epam.spring.core.shared.BaseDAO;
import com.epam.spring.core.events.Event;
import com.epam.spring.core.tickets.Ticket;
import com.epam.spring.core.users.User;
import org.joda.time.LocalDateTime;

import java.util.List;

public interface TicketDAO extends BaseDAO<Ticket>{

    Ticket update(Ticket ticket);

    List<Ticket> findByUser(User user);

    List<Ticket> findByEventAndDate(Event event, LocalDateTime date);

    List<Ticket> findFreeByEventAndDate(Event event, LocalDateTime date);
}
