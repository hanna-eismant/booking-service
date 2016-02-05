package com.epam.spring.core.discounts.strategies;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.users.User;
import com.epam.spring.core.users.services.UserService;
import org.joda.time.LocalDateTime;

public class DiscountStrategyTenTicketImpl implements DiscountStrategy {

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Double calculate(User user, Event event, LocalDateTime date) {
        int ticketsCount = userService.getBookedTicketsCount(user);

        if ((ticketsCount % 10) == 9) { // todo: magic numbers!
            return 0.5;
        } else {
            return 0.0;
        }
    }
}
