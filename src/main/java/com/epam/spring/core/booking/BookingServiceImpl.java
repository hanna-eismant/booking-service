package com.epam.spring.core.booking;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.tickets.Ticket;
import com.epam.spring.core.tickets.dao.TicketDAO;
import com.epam.spring.core.tickets.services.TicketService;
import com.epam.spring.core.users.User;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookingService")
public class BookingServiceImpl implements BookingService {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketDAO ticketDAO;


    @Override
    public Ticket bookTicket(User user, Ticket ticket) {
        ticket.user = user;
        ticket.discountPrice = ticketService.getTicketPrice(ticket.event, ticket.date, ticket.seat, ticket.isVip, user);
        return ticketDAO.update(ticket);
    }

    @Override
    public List<Ticket> getTicketsForEvent(Event event, LocalDateTime date) {
        return ticketDAO.findByEventAndDate(event, date);
    }

    @Override
    public List<Ticket> getFreeTicketsForEvent(Event event, LocalDateTime date) {
        return ticketDAO.findFreeByEventAndDate(event, date);
    }
}
