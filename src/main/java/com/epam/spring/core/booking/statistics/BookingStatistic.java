package com.epam.spring.core.booking.statistics;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.tickets.Ticket;
import com.epam.spring.core.users.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.joda.time.LocalDateTime;

public interface BookingStatistic {
    void countBooked(User user, Ticket ticket);

    Object countPriceQueried(ProceedingJoinPoint joinPoint, Event event, LocalDateTime date, Integer seat, User user) throws Throwable;

    Long getBookedStatistic(Event event);

    Long getPriceQueriedStatistic(Event event);
}
