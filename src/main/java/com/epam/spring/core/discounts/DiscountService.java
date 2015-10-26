package com.epam.spring.core.discounts;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.users.User;

import java.util.Date;

public interface DiscountService {
    Double getDiscount(User user, Event event, Date date);
}
