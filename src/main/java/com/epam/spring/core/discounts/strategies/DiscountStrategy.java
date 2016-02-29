package com.epam.spring.core.discounts.strategies;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.Show;
import com.epam.spring.core.users.User;
import org.joda.time.LocalDateTime;

public interface DiscountStrategy {

    @Deprecated
    Double calculate(User user, Event event, LocalDateTime date);

    Double calculate(User user, Show show);
}
