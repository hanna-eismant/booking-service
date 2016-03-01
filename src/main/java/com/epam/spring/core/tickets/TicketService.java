package com.epam.spring.core.tickets;

import java.util.List;

import org.joda.time.LocalDateTime;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.shared.exceptions.NotFoundException;
import com.epam.spring.core.users.User;

public interface TicketService {

    Double getTicketPrice(Event event, LocalDateTime date, Integer seat, boolean isVip, User user);

    Double getTicketPrice(Ticket ticket, User user);

    /**
     * Find all booked tickets by user's name.
     *
     * @param userName
     *            user's name for search.
     * @return list of all booked tickets by user. If user has no booked tickets then return empty list.
     */
    List<Ticket> getBookedTickets(String userName);

    /**
     * Calculate how many tickets has booked by specified user.
     */
    Long getBookedTicketsCount(User user);

    /**
     *
     * @param showId
     * @return
     */
    List<Ticket> getForShow(Long showId);

    Ticket update(Ticket ticket);

    Ticket getById(Long id) throws NotFoundException;
}
