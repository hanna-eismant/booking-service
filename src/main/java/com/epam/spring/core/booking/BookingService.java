package com.epam.spring.core.booking;

import com.epam.spring.core.shared.exceptions.NotEnoughMoneyException;
import com.epam.spring.core.shared.exceptions.NotFoundException;
import com.epam.spring.core.shared.exceptions.TicketAlreadyBookedException;
import com.epam.spring.core.tickets.Ticket;

public interface BookingService {

    /**
     * If ticket is already booked by this user → do nothing. If ticket is already booked by another user → throw exception
     *
     *
     * @param userName
     * @param ticketId
     * @return
     */
    Ticket bookTicket(String userName, Long ticketId) throws NotFoundException, TicketAlreadyBookedException, NotEnoughMoneyException;
}
