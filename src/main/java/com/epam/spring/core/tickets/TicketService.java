package com.epam.spring.core.tickets;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.users.User;
import org.joda.time.LocalDateTime;

import java.util.List;

public interface TicketService {

    Double getTicketPrice(Event event, LocalDateTime date, Integer seat, boolean isVip, User user);

    /**
     * Find all booked tickets by user.
     *
     * @param user user for search.
     * @return list of all booked tickets by user. If user has no booked tickets then return empty list.
     */
    List<Ticket> getBookedTickets(User user);

    /**
     * Calculate how many tickets has booked specified user.
     */
    int getBookedTicketsCount(User user);
}
