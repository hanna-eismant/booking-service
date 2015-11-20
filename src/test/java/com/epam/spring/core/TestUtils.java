package com.epam.spring.core;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.Rating;
import com.epam.spring.core.tickets.Ticket;
import com.epam.spring.core.users.User;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestUtils {

    // ********
    // data from test properties files
    // ********

    public static String AUDITORIUM_ONE_NAME = "auditorium test one";
    public static Integer AUDITORIUM_ONE_SEATS = 24;
    public static List<Integer> AUDITORIUM_ONE_VIPSEATS = new ArrayList<>(Arrays.asList(4, 5, 6, 7, 8, 9, 10));

    public static String AUDITORIUM_TWO_NAME = "auditorium test two";
    public static Integer AUDITORIUM_TWO_SEATS = 12;
    public static List<Integer> AUDITORIUM_TWO_VIPSEATS = new ArrayList<>(Collections.singletonList(2));


    // ********
    // data inserted thru sql
    // ********
    public static User USER_JHON = new User();
    public static User USER_JANE = new User();

    public static int JHON_BOOKED_COUNT = 10;
    public static int JANE_BOOKED_COUNT = 3;

    public static Event HOBBIT_EVENT = new Event();
    public static Event GAMER_EVENT = new Event();

    public static Ticket TICKET = new Ticket();

    public static LocalDateTime HOBBIT_TIME_ONE = LocalDateTime.parse("2015-06-06T14:00:00.000");
    public static LocalDateTime HOBBIT_TIME_TWO = LocalDateTime.parse("2015-06-07T22:00:00.000");
    public static LocalDateTime GAMER_TIME = LocalDateTime.parse("2015-10-17T20:00:00.000");

    public static int HOBBIT_TOTAL_TICKETS_ONE = 24;
    public static int HOBBIT_FREE_TICKETS_ONE = 23;
    public static int HOBBIT_TOTAL_TICKETS_TWO = 24;
    public static int HOBBIT_FREE_TICKETS_TWO = 24;

    public static int GAMER_TOTAL_TICKETS = 12;
    public static int GAMER_FREE_TICKETS = 0;

    static {
        USER_JHON.id = 255L;
        USER_JHON.name = "jhon";
        USER_JHON.email = "jhon@mail";
        USER_JHON.birthday = LocalDate.parse("1987-05-05");

        USER_JANE.id = 254L;
        USER_JANE.name = "jane";
        USER_JANE.email = "jane@mail";
        USER_JANE.birthday = LocalDate.parse("1986-10-17");

        HOBBIT_EVENT.id = 255L;
        HOBBIT_EVENT.name = "Hobbit";
        HOBBIT_EVENT.basePrice = 8_000.0;
        HOBBIT_EVENT.rating = Rating.HIGH;

        GAMER_EVENT.id = 254L;
        GAMER_EVENT.name = "The Gamer";
        GAMER_EVENT.basePrice = 5_000.0;
        GAMER_EVENT.rating = Rating.MID;

        // (251, 5, TRUE, '2015-06-06T14:00:00.000', 19200, NULL, 255, NULL),
        TICKET.id = 251L;
        TICKET.seat = 5;
        TICKET.isVip = true;
        TICKET.date = HOBBIT_TIME_ONE;
        TICKET.basePrice = 19_200d;
        TICKET.event = HOBBIT_EVENT;
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
