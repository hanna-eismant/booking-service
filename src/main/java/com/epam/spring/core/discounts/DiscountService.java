package com.epam.spring.core.discounts;

import org.joda.time.LocalDateTime;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.Show;
import com.epam.spring.core.users.User;

public interface DiscountService {

    @Deprecated
    Double getDiscount(User user, Event event, LocalDateTime date);

    Double getDiscount(User user, Show show);
}
