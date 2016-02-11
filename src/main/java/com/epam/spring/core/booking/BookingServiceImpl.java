package com.epam.spring.core.booking;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.tickets.Ticket;
import com.epam.spring.core.tickets.TicketDAO;
import com.epam.spring.core.tickets.TicketService;
import com.epam.spring.core.users.User;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service("bookingService")
public class BookingServiceImpl implements BookingService {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketDAO ticketDAO;


    @Override
    public Ticket bookTicket(User user, Ticket ticket) {
//        todo: change after refactoring data model

//        ticket.setUser(user);
//        ticket.setDiscountPrice(ticketService.getTicketPrice(ticket.event, ticket.date, ticket.getSeat(), ticket.isVip(), user));
        return ticketDAO.update(ticket);
    }

    @Override
    public List<Ticket> getTicketsForEvent(Event event, LocalDateTime date) {
        //        todo: change after refactoring data model
        return Collections.emptyList(); //ticketDAO.findByEventAndDate(event, date);
    }

    @Override
    public List<Ticket> getFreeTicketsForEvent(Event event, LocalDateTime date) {
        //        todo: change after refactoring data model
        return Collections.emptyList();//ticketDAO.findFreeByEventAndDate(event, date);
    }
}
