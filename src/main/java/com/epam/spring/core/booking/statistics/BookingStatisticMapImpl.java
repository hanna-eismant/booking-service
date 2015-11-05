package com.epam.spring.core.booking.statistics;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.tickets.Ticket;
import com.epam.spring.core.users.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.joda.time.LocalDateTime;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class BookingStatisticMapImpl implements BookingStatistic {

    /**
     * How many times tickets were booked for each event.
     */
    private Map<Event, Integer> bookedCounter = new HashMap<>();

    /**
     * How many times prices were queried for each event.
     */
    private Map<Event, Integer> priceQueriedCounter = new HashMap<>();


    @Override
    @AfterReturning(value = "execution(* com.epam.spring.core.booking.BookingService*.bookTicket(..)) && args(user,ticket)", argNames = "user,ticket")
    public void countBooked(User user, Ticket ticket) {
        Event event = ticket.event;

        if (!bookedCounter.containsKey(event)) {
            bookedCounter.put(event, 0);
        }

        bookedCounter.put(event, bookedCounter.get(event) + 1);

        // if we book ticket then call getTicketPrice() within bookTicket()
        // therefore we increment priceQueriedCounter here

        if (!priceQueriedCounter.containsKey(event)) {
            priceQueriedCounter.put(event, 0);
        }

        priceQueriedCounter.put(event, priceQueriedCounter.get(event) + 1);
    }

    @Override
    @Around(value = "execution(* com.epam.spring.core.booking.BookingService*.getTicketPrice(..)) && args(event,date,seat,user)", argNames = "joinPoint,event,date,seat,user")
    public Object countPriceQueried(ProceedingJoinPoint joinPoint, Event event, LocalDateTime date, Integer seat, User user) throws Throwable {
        if (!priceQueriedCounter.containsKey(event)) {
            priceQueriedCounter.put(event, 0);
        }

        priceQueriedCounter.put(event, priceQueriedCounter.get(event) + 1);
        return joinPoint.proceed(new Object[]{event, date, seat, user});
    }

    @Override
    public Integer getBookedStatistic(Event event) {
        if (bookedCounter.containsKey(event)) {
            return bookedCounter.get(event);
        }

        return null;
    }

    @Override
    public Integer getPriceQueriedStatistic(Event event) {
        if (priceQueriedCounter.containsKey(event)) {
            return priceQueriedCounter.get(event);
        }

        return null;
    }

    @Override
    public void removeAll() {
        bookedCounter.clear();
        priceQueriedCounter.clear();
    }
}
