package com.epam.spring.core.booking.statistics;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.shared.Statistic;
import com.epam.spring.core.shared.StatisticDAO;
import com.epam.spring.core.tickets.Ticket;
import com.epam.spring.core.users.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
public class BookingStatisticImpl implements BookingStatistic {

    private static final String BOOKED_NAME = "booked";
    private static final String PRICE_QUERIED_NAME = "price queried";

    @Autowired
    private StatisticDAO statisticDAO;

    @Override
    @AfterReturning(value = "execution(* com.epam.spring.core.booking.BookingService*.bookTicket(..)) && args(user,ticket)", argNames = "user,ticket")
    public void countBooked(User user, Ticket ticket) {
        Event event = ticket.event;

        statisticDAO.incrementCounter(BOOKED_NAME, event.name);

        // if we book ticket then call getTicketPrice() within bookTicket()
        // therefore we increment priceQueriedCounter here
        statisticDAO.incrementCounter(PRICE_QUERIED_NAME, event.name);
    }

    @Override
    @Around(value = "execution(* com.epam.spring.core.booking.BookingService*.getTicketPrice(..)) && args(event,date,seat,user)", argNames = "joinPoint,event,date,seat,user")
    public Object countPriceQueried(ProceedingJoinPoint joinPoint, Event event, LocalDateTime date, Integer seat, User user) throws Throwable {
        statisticDAO.incrementCounter(PRICE_QUERIED_NAME, event.name);
        return joinPoint.proceed(new Object[]{event, date, seat, user});
    }

    @Override
    public Long getBookedStatistic(Event event) {
        Statistic statistic = statisticDAO.findByNameAndType(BOOKED_NAME, event.name);
        return statistic.counter;
    }

    @Override
    public Long getPriceQueriedStatistic(Event event) {
        Statistic statistic = statisticDAO.findByNameAndType(PRICE_QUERIED_NAME, event.name);
        return statistic.counter;
    }
}
