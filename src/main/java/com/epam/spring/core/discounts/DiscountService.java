package com.epam.spring.core.discounts;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.Show;
import com.epam.spring.core.users.User;
import org.joda.time.LocalDateTime;

public interface DiscountService {

    @Deprecated
    Double getDiscount(User user, Event event, LocalDateTime date);

    Double getDiscount(User user, Show show);
}
