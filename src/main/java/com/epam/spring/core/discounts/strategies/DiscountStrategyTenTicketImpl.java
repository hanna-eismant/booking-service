package com.epam.spring.core.discounts.strategies;

import org.joda.time.LocalDateTime;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.Show;
import com.epam.spring.core.tickets.TicketService;
import com.epam.spring.core.users.User;
import com.epam.spring.core.users.UserService;

public class DiscountStrategyTenTicketImpl implements DiscountStrategy {

    private UserService userService;

    private TicketService ticketService;

    public void setUserService(final UserService _userService) {
        userService = _userService;
    }

    public void setTicketService(final TicketService _ticketService) {
        ticketService = _ticketService;
    }

    @Override
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
