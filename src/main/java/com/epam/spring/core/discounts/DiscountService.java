package com.epam.spring.core.discounts;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.users.User;
import org.joda.time.LocalDateTime;

public interface DiscountService {
    Double getDiscount(User user, Event event, LocalDateTime date);
}
