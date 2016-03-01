package com.epam.spring.core.discounts.strategies;

import org.joda.time.LocalDateTime;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.Show;
import com.epam.spring.core.users.User;

public interface DiscountStrategy {

    @Deprecated
    Double calculate(User user, Event event, LocalDateTime date);

    Double calculate(User user, Show show);
}
