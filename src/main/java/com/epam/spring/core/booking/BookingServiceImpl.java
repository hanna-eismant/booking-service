package com.epam.spring.core.booking;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.tickets.Ticket;
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

    // private

    @Override
    public Ticket bookTicket(User user, Ticket ticket) {
        // todo: check parameters

        Double ticketPrice = ticketService.getTicketPrice(ticket, user);
        ticket.setDiscountPrice(ticketPrice);

        Double money = user.getAccount().getMoney();
        money -= ticketPrice;

        ticket.setUser(user);
        user.getAccount().setMoney(money);

        return ticket;
    }

    @Override
    public List<Ticket> getTicketsForEvent(Event event, LocalDateTime date) {
        // todo: change after refactoring data model
        return Collections.emptyList(); // ticketDAO.findByEventAndDate(event, date);
    }

    @Override
    public List<Ticket> getFreeTicketsForEvent(Event event, LocalDateTime date) {
        // todo: change after refactoring data model
        return Collections.emptyList();// ticketDAO.findFreeByEventAndDate(event, date);
    }
}
