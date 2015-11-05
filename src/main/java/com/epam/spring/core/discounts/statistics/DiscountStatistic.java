package com.epam.spring.core.discounts.statistics;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.users.User;
import org.aspectj.lang.JoinPoint;
import org.joda.time.LocalDateTime;

public interface DiscountStatistic {
    void countTotal(JoinPoint joinPoint);

    void countTenTicket(User user, Event event, LocalDateTime date);

    void countBirthday(User user, Event event, LocalDateTime date);

    Integer getTotalStatistic(Class<?> strategy);

    Integer getTenTicketStatistic(User user);

    Integer getBirthdayStatistic(User user);

    void removeAll();
}
