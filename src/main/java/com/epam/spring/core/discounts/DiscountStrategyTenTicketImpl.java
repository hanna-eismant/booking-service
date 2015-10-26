package com.epam.spring.core.discounts;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.users.User;

import java.util.Date;

public class DiscountStrategyTenTicketImpl implements DiscountStrategy {

    @Override
    public Integer calculate(User user, Event event, Date date) {
        return null;
    }
}
