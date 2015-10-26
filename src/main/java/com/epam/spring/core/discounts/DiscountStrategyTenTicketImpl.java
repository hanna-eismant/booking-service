package com.epam.spring.core.discounts;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.services.EventService;
import com.epam.spring.core.tickets.Ticket;
import com.epam.spring.core.users.User;
import org.joda.time.LocalDateTime;

import java.util.List;

public class DiscountStrategyTenTicketImpl implements DiscountStrategy {

    private EventService eventService;

    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    public DiscountStrategyTenTicketImpl(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public Double calculate(User user, Event event, LocalDateTime date) {
        int ticketsCount = getBookedTicketsCount(user);

        if ((ticketsCount % 10) == 9) {
            return 0.5;
        } else {
            return 0.0;
        }
    }

    private int getBookedTicketsCount(User user) {
        int result = 0;

        List<Event> allEvents = eventService.getAll();

        for (Event event : allEvents) {
            List<Ticket> tickets = event.getTickets();
            for (Ticket ticket : tickets) {
                if (user.equals(ticket.user)) {
                    result++;
                }
            }
        }

        return result;
    }
}
