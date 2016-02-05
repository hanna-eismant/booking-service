package com.epam.spring.core.discounts.strategies;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.users.User;
import org.joda.time.DateTimeComparator;
import org.joda.time.DateTimeFieldType;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

public class DiscountStrategyBirthdayImpl implements DiscountStrategy {

    private static final DateTimeComparator COMPARATOR =
            DateTimeComparator.getInstance(DateTimeFieldType.dayOfMonth(), DateTimeFieldType.monthOfYear());

    @Override
    public Double calculate(User user, Event event, LocalDateTime date) {
        int compare = COMPARATOR.compare(date.toDateTime(), user.birthday.toDateTime(LocalTime.now()));

        if (compare == 0) {
            return 0.05; // todo: magic number!
        }
        else {
            return 0.0;
        }
    }
}
