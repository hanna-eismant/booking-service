package com.epam.spring.core.booking;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.tickets.Ticket;
import com.epam.spring.core.users.User;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.joda.time.LocalDateTime;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class BookingStatistic {

    /**
     * How many times tickets were booked for each event.
     */
    private Map<Event, Integer> bookedCounter = new HashMap<>();

    /**
     * How many times prices were queried for each event.
     */
    private Map<Event, Integer> priceQueriedCounter = new HashMap<>();


    @AfterReturning(value = "execution(* com.epam.spring.core.booking.BookingService*.bookTicket(..)) && args(user,ticket)", argNames = "user,ticket")
    public void countBooked(User user, Ticket ticket) {
        Event event = ticket.event;

        if (!bookedCounter.containsKey(event)) {
            bookedCounter.put(event, 0);
        }

        bookedCounter.put(event, bookedCounter.get(event) + 1);
    }

    @AfterReturning(value = "execution(* com.epam.spring.core.booking.BookingService*.getTicketPrice(..)) && args(event,date,seat,user) ", argNames = "event,date,seat,user")
    public void countPriceQueried(Event event, LocalDateTime date, Integer seat, User user) {

        System.out.println("******************");

        if (!priceQueriedCounter.containsKey(event)) {
            priceQueriedCounter.put(event, 0);
        }

        priceQueriedCounter.put(event, priceQueriedCounter.get(event) + 1);
    }

    public void printBookedStatistic() {
        System.out.println("Booking statistic:");

        for (Event event : bookedCounter.keySet()) {
            System.out.println(event.name + ": " + bookedCounter.get(event));
        }
    }

    public void printPriceQueriedStatistic() {
        System.out.println("Price Queried statistic:");

        for (Event event : priceQueriedCounter.keySet()) {
            System.out.println(event.name + ": " + priceQueriedCounter.get(event));
        }
    }


    public void print() {
        printBookedStatistic();
        System.out.println();
        printPriceQueriedStatistic();
    }
}
