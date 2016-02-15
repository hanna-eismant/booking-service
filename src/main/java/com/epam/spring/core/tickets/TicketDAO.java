package com.epam.spring.core.tickets;

import com.epam.spring.core.shared.BaseDAO;
import com.epam.spring.core.users.User;

import java.util.List;

public interface TicketDAO extends BaseDAO<Ticket> {

    Ticket update(Ticket ticket);

    List<Ticket> findByUser(User user);

    List<Ticket> findByShow(Long showId);
}
