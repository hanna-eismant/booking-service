package com.epam.spring.core;

import com.epam.spring.core.auditoriums.Auditorium;
import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.Rating;
import com.epam.spring.core.events.Show;
import com.epam.spring.core.tickets.Ticket;
import com.epam.spring.core.users.User;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.util.Arrays;

public abstract class TestConstants {

    // ********
    // data inserted thru sql
    // ********
    public static User USER_JHON = new User();
    public static User USER_JANE = new User();

    public static Event HOBBIT_EVENT = new Event();
    public static Event GAMER_EVENT = new Event();

    public static Show HOBBIT_SHOW_ONE = new Show();

    public static Auditorium AUDITORIUM_ONE = new Auditorium();

    public static Ticket TICKET = new Ticket();

    public static LocalDateTime HOBBIT_TIME_ONE = LocalDateTime.parse("2015-06-06T14:00:00.000");

    public static int HOBBIT_TOTAL_TICKETS_ONE = 24;
    public static int HOBBIT_FREE_TICKETS_ONE = 23;

    static {
        USER_JHON.setId(1L);
        USER_JHON.setName("jhon");
        USER_JHON.setEmail("jhon@mail");
        USER_JHON.setBirthday(LocalDate.parse("1987-05-05"));

        USER_JANE.setId(2L);
        USER_JANE.setName("jane");
        USER_JANE.setEmail("jane@mail");
        USER_JANE.setBirthday(LocalDate.parse("1986-10-17"));

        HOBBIT_EVENT.setId(1L);
        HOBBIT_EVENT.setName("Hobbit");
        HOBBIT_EVENT.setBasePrice(8_000.0);
        HOBBIT_EVENT.setRating(Rating.HIGH);

        AUDITORIUM_ONE.setId(1L);
        AUDITORIUM_ONE.setName("Big party hall");
        AUDITORIUM_ONE.setSeats(24);
        AUDITORIUM_ONE.getVipSeats().addAll(Arrays.asList(4, 5, 6, 7, 8, 9, 10));

        HOBBIT_SHOW_ONE.setId(1L);
        HOBBIT_SHOW_ONE.setEvent(HOBBIT_EVENT);
        HOBBIT_SHOW_ONE.setDate(HOBBIT_TIME_ONE);
        HOBBIT_SHOW_ONE.setAuditorium(AUDITORIUM_ONE);
        HOBBIT_SHOW_ONE.setFreeTicketCount(HOBBIT_FREE_TICKETS_ONE);

        GAMER_EVENT.setId(2L);
        GAMER_EVENT.setName("The Gamer");
        GAMER_EVENT.setBasePrice(5_000.0);
        GAMER_EVENT.setRating(Rating.MID);

        TICKET.setId(251L);
        TICKET.setSeat(5);
        TICKET.setVip(true);
        TICKET.setBasePrice(19_200d);
    }


    // ********
    // new data for test (will be inserted thru tests)
    // ********

    // user one
    public static final String USER_NAME = "user";
    public static final String USER_EMAIL = "user@mail";
    public static final String USER_BIRTHDAY_S = "1987-04-04";
    public static final String USER_PASSWORD = "pass";
    public static final LocalDate USER_BIRTHDAY = LocalDate.parse(USER_BIRTHDAY_S);

    // user two
    public static final String ADMIN_NAME = "admin";
    public static final String ADMIN_EMAIL = "admin@mail";
    public static final String ADMIN_BIRTHDAY_S = "1987-04-04";
    public static final LocalDate ADMIN_BIRTHDAY = LocalDate.parse(ADMIN_BIRTHDAY_S);

    // event
    public static final String EVENT_NAME = "Test event";
    public static final Double BASE_PRICE = 10_000.0;
    public static final Double BASE_PRICE_WITH_RATING = 12_000.0;
    public static final Rating RATING = Rating.HIGH;

    public static final String EVENT_DATE_S = "2015-06-06T14:00:00.000";
    public static final LocalDateTime EVENT_DATE = LocalDateTime.parse(EVENT_DATE_S);

    // ticket
    public static final String TICKET_DATE_S = "2015-06-06T14:00:00.000";
    public static final LocalDateTime TICKET_DATE = LocalDateTime.parse(TICKET_DATE_S);
    public static final Integer TICKET_SEAT = 25;
    public static final Double TICKET_BASE_PRICE = 9_600d;
}
