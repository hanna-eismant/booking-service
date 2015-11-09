package com.epam.spring.core.tickets.dao;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.tickets.Ticket;
import com.epam.spring.core.users.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketDAOImpl implements TicketDAO {

    @Override
    public Ticket create(Ticket ticket) {
        return null;
    }

    @Override
    public Ticket update(Ticket ticket) {
        return null;
    }

    @Override
    public List<Ticket> findByEvent(Event event) {
        return null;
    }

    @Override
    public List<User> findByUser(User user) {
        return null;
    }
}
