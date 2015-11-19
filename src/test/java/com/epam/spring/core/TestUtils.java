package com.epam.spring.core;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.Rating;
import com.epam.spring.core.tickets.Ticket;
import com.epam.spring.core.users.User;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

public class TestUtils {

    // ********
    // data inserted thru sql
    // ********
    public static User USER_JHON = new User();
    public static User USER_JANE = new User();

    public static Event EVENT_HOBBIT = new Event();
    public static Event EVENT_GAMER = new Event();

    public static Ticket TICKET = new Ticket();


    static {
        USER_JHON.id = 255L;
        USER_JHON.name = "jhon";
        USER_JHON.email = "jhon@mail";
        USER_JHON.birthday = LocalDate.parse("1987-05-05");

        USER_JANE.id = 254L;
        USER_JANE.name = "jane";
        USER_JANE.email = "jane@mail";
        USER_JANE.birthday = LocalDate.parse("1986-10-17");

        EVENT_HOBBIT.id = 255L;
        EVENT_HOBBIT.name = "Hobbit";
        EVENT_HOBBIT.basePrice = 8_000.0;
        EVENT_HOBBIT.rating = Rating.HIGH;

        EVENT_GAMER.id = 254L;
        EVENT_GAMER.name = "The Gamer";
        EVENT_GAMER.basePrice = 5_000.0;
        EVENT_GAMER.rating = Rating.MID;

        // (251, 5, TRUE, '2015-06-06T14:00:00.000', 19200, NULL, 255, NULL),
        TICKET.id = 251L;
        TICKET.seat = 5;
        TICKET.isVip = true;
        TICKET.date = LocalDateTime.parse("2015-06-06T14:00:00.000");
        TICKET.basePrice = 19_200d;
        TICKET.event = EVENT_HOBBIT;
    }

    // ********
    // new data for test (will be inserted thru tests)
    // ********

    // user one
    public static final String USER_NAME = "user";
    public static final String USER_EMAIL = "user@mail";
    public static final String USER_BIRTHDAY_S = "1987-04-04";
    public static final LocalDate USER_BIRTHDAY = LocalDate.parse(USER_BIRTHDAY_S);

    // user two
    public static final String ADMIN_NAME = "admin";
    public static final String ADMIN_EMAIL = "admin@mail";
    public static final String ADMIN_BIRTHDAY_S = "1987-04-04";
    public static final LocalDate ADMIN_BIRTHDAY = LocalDate.parse(ADMIN_BIRTHDAY_S);

    // event
    public static final String EVENT_NAME = "Test event";
    public static final Double BASE_PRICE = 10_000.0;
    public static final Rating RATING = Rating.MID;

    public static final String EVENT_DATE_S = "2015-06-06T14:00:00.000";
    public static final LocalDateTime EVENT_DATE = LocalDateTime.parse(EVENT_DATE_S);

    // ticket
    public static final String TICKET_DATE_S = "2015-06-06T14:00:00.000";
    public static final LocalDateTime TICKET_DATE = LocalDateTime.parse(TICKET_DATE_S);
    public static final Integer TICKET_SEAT = 25;
    public static final Double TICKET_BASE_PRICE = 9_600d;
}
