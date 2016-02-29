package com.epam.spring.core.discounts.strategies;

import org.joda.time.DateTimeComparator;
import org.joda.time.DateTimeFieldType;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.Show;
import com.epam.spring.core.users.User;

public class DiscountStrategyBirthdayImpl implements DiscountStrategy {

    private static final DateTimeComparator COMPARATOR = DateTimeComparator.getInstance(DateTimeFieldType.dayOfMonth(), DateTimeFieldType.monthOfYear());

    @Override
    public Double calculate(User user, Event event, LocalDateTime date) {
        int compare = COMPARATOR.compare(date.toDateTime(), user.getBirthday().toDateTime(LocalTime.now()));

        if (compare == 0) {
            return 0.05; // todo: magic number!
        } else {
            return 0.0;
        }
    }

    @Override
    public Double calculate(final User user, final Show show) {
        int compare = COMPARATOR.compare(show.getDate().toDateTime(), user.getBirthday().toDateTime(LocalTime.now()));

        if (compare == 0) {
            return 0.05; // todo: magic number!
        } else {
            return 0.0;
        }
    }
}
