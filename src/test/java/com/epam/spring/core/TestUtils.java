package com.epam.spring.core;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.Rating;
import com.epam.spring.core.users.User;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

public class TestUtils {

    // ********
    // data inserted thru sql
    // ********
    public static User jhon = new User();
    public static User jane = new User();

    public static Event hobbit = new Event();
    public static Event gamer = new Event();


    static {
        jhon.id = 255L;
        jhon.name = "jhon";
        jhon.email = "jhon@mail";
        jhon.birthday = LocalDate.parse("1987-05-05");

        jane.id = 254L;
        jane.name = "jane";
        jane.email = "jane@mail";
        jane.birthday = LocalDate.parse("1986-10-17");

        hobbit.id = 255L;
        hobbit.name = "Hobbit";
        hobbit.basePrice = 9_600.0;
        hobbit.rating = Rating.HIGH;

        gamer.id = 254L;
        gamer.name = "The Gamer";
        gamer.basePrice = 5_000.0;
        gamer.rating = Rating.MID;
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
}
