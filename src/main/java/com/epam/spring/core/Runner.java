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
import org.joda.time.LocalDateTime;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Runner {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        // prepare
        context = new ClassPathXmlApplicationContext("classpath:spring.xml");

        // get services
        UserService userService = context.getBean(UserService.class);
        EventService eventService = context.getBean(EventService.class);
        BookingService bookingService = context.getBean(BookingService.class);

        // users
        User hanna = null;
        try {
            hanna = userService.register("Hanna", "Hanna@Mail", LocalDate.parse("1990-12-15"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        User admin = null;
        try {
            admin = userService.register("Admin", "Admin@Mail", LocalDate.parse("1990-12-10"));
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
        eventService.assignAuditorium(eventOne, auditoriums.get(0), LocalDateTime.parse("2015-12-15T14:00:00.000"));
        eventService.assignAuditorium(eventOne, auditoriums.get(0), LocalDateTime.parse("2015-12-15T23:15:00.000"));
        eventService.assignAuditorium(eventTwo, auditoriums.get(0), LocalDateTime.parse("2015-12-16T14:00:00.000"));

        // book ticket
        for (int i = 1; i < 4; i++) {
            bookingService.bookTicket(hanna, eventOne.getTickets().get(i));
        }

        for (int i = 0; i < 6; i++) {
            bookingService.bookTicket(hanna, eventTwo.getTickets().get(i));
        }

        for (int i = 10; i < 16; i++) {
            bookingService.bookTicket(admin, eventOne.getTickets().get(i));
        }

        List<Ticket> hannaTickets = userService.getBookedTickets(hanna);
        System.out.println();
        System.out.println("Hanna's tickets: " + hannaTickets.size());


        for (Ticket ticket : hannaTickets) {
            System.out.println(ticket);
        }

        List<Ticket> adminTickets = userService.getBookedTickets(admin);
        System.out.println();
        System.out.println("Admin's tickets: " + adminTickets.size());

        for (Ticket ticket : adminTickets) {
            System.out.println(ticket);
        }
    }
}
