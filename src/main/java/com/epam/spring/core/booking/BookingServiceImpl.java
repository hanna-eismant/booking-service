package com.epam.spring.core.booking;

import com.epam.spring.core.discounts.DiscountService;
import com.epam.spring.core.events.Event;
import com.epam.spring.core.tickets.Ticket;
import com.epam.spring.core.tickets.dao.TicketDAO;
import com.epam.spring.core.users.User;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("bookingService")
public class BookingServiceImpl implements BookingService {

    @Autowired
    private DiscountService discountService;

    @Autowired
    private TicketDAO ticketDAO;

    @Override
    public Double getTicketPrice(Event event, LocalDateTime date, Integer seat, User user) {
        Double discountPercent = discountService.getDiscount(user, event, date);
        return event.basePrice * (1 - discountPercent);
    }

    @Override
    public void bookTicket(User user, Ticket ticket) {
        ticket.user = user;
        ticket.discountPrice = getTicketPrice(ticket.event, ticket.date, ticket.seat, user);
        // todo: call appropriate DAO method
    }

    @Override
    public List<Ticket> getTicketsForEvent(Event event, LocalDateTime date) {
        List<Ticket> result = new ArrayList<>();
        // todo: call appropriate DAO method
        return result;
    }

    @Override
    public List<Ticket> getFreeTicketsForEvent(Event event, LocalDateTime date) {
        List<Ticket> result = new ArrayList<>();
        ticketDAO.findByEventAndDate(event, date);
        return result;
    }

    public void setDiscountService(DiscountService discountService) {
        this.discountService = discountService;
    }

    public void setTicketDAO(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }
}
