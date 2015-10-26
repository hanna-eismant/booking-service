package com.epam.spring.core.booking;

import com.epam.spring.core.discounts.DiscountService;
import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.services.EventService;
import com.epam.spring.core.tickets.Ticket;
import com.epam.spring.core.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private EventService eventService;

    private DiscountService discountService;

    public EventService getEventService() {
        return eventService;
    }

    public void setDiscountService(DiscountService discountService) {
        this.discountService = discountService;
    }

    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public Double getTicketPrice(Event event, Date date, Integer seat, User user) {
        Double discount = discountService.getDiscount(user, event, date);
        return event.basePrice * discount;
    }

    @Override
    public void bookTicket(User user, Ticket ticket) {
        ticket.user = user;
        ticket.discountPrice = getTicketPrice(ticket.event, ticket.date, ticket.seat, user);
    }

    @Override
    public List<Ticket> getTicketsForEvent(Event event, Date date) {
        List<Ticket> result = new ArrayList<>();
        List<Ticket> tickets = event.getTickets();

        for (Ticket ticket : tickets) {
            if (date.equals(ticket.date)) {
                result.add(ticket);
            }
        }

        return result;
    }
}
