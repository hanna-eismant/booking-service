package com.epam.spring.core;

import com.epam.spring.core.auditoriums.Auditorium;
import com.epam.spring.core.auditoriums.services.AuditoriumService;
import com.epam.spring.core.booking.BookingService;
import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.Rating;
import com.epam.spring.core.events.services.EventService;
import com.epam.spring.core.tickets.Ticket;
import com.epam.spring.core.users.User;
import com.epam.spring.core.users.services.UserService;
import org.joda.time.LocalDate;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Calendar;
import java.util.List;

public class Runner {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        // prepare
        context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        Calendar calendar = Calendar.getInstance();

        // get services
        UserService userService = context.getBean(UserService.class);
        EventService eventService = context.getBean(EventService.class);
        BookingService bookingService = context.getBean(BookingService.class);

        // user
        calendar.set(2015, Calendar.DECEMBER, 15, 14, 15, 0);

        User hanna = null;
        try {
            hanna = userService.register("Hanna", "Hanna@Mail", LocalDate.parse("1990-12-15"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // event one
        Event eventOne = eventService.create("Terminator 8", 25_000.0, Rating.HIGH);

        // event two
        Event eventTwo = eventService.create("Just Married", 5_000.0, Rating.LOW);

        // auditoriums
        AuditoriumService auditoriumService = context.getBean(AuditoriumService.class);
        List<Auditorium> auditoriums = auditoriumService.getAuditoriums();

        // assign
        calendar.set(2015, Calendar.DECEMBER, 15, 14, 0, 0);
        eventService.assignAuditorium(eventOne, auditoriums.get(0), calendar.getTime());

        calendar.set(2015, Calendar.DECEMBER, 15, 23, 15, 0);
        eventService.assignAuditorium(eventOne, auditoriums.get(0), calendar.getTime());

        calendar.set(2015, Calendar.DECEMBER, 15, 14, 0, 0);
        eventService.assignAuditorium(eventTwo, auditoriums.get(0), calendar.getTime());

        // book ticket
        Ticket ticket = eventOne.getTickets().get(0);
        bookingService.bookTicket(hanna, ticket);

    }
}
