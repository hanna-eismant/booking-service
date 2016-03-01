package com.epam.spring.core.discounts.strategies;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.Show;
import com.epam.spring.core.tickets.TicketService;
import com.epam.spring.core.users.User;
import org.joda.time.LocalDateTime;

public class DiscountStrategyTenTicketImpl implements DiscountStrategy {

    private TicketService ticketService;

    public void setTicketService(final TicketService _ticketService) {
        ticketService = _ticketService;
    }

    @Override
    @Deprecated
    public Double calculate(User user, Event event, LocalDateTime date) {
        Long ticketsCount = ticketService.getBookedTicketsCount(user);

        if ((ticketsCount % 10) == 9) { // todo: magic numbers!
            return 0.5;
        } else {
            return 0.0;
        }
    }

    @Override
    public Double calculate(final User user, final Show show) {
        Long ticketsCount = ticketService.getBookedTicketsCount(user);

        if ((ticketsCount % 10) == 9) { // todo: magic numbers!
            return 0.5;
        } else {
            return 0.0;
        }
    }
}
