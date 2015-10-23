package com.epam.spring.core;

import com.epam.spring.core.events.Rating;
import com.epam.spring.core.events.services.EventService;
import com.epam.spring.core.users.User;
import com.epam.spring.core.users.services.UserService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

        // user
        calendar.set(2015, Calendar.DECEMBER, 15, 14, 15);
        User hanna = userService.register("Hanna", "Hanna@Mail", calendar.getTime());

        // event one
        List<Date> eventOneDates = new ArrayList<Date>();

        calendar.set(2015, Calendar.DECEMBER, 15, 14, 0);
        eventOneDates.add(calendar.getTime());

        calendar.set(2015, Calendar.DECEMBER, 2, 9, 0);
        eventOneDates.add(calendar.getTime());

        eventService.create("Birthday party", eventOneDates, eventOneDates, 25000, Rating.HIGH);

        // event two
        List<Date> eventTwoDates = new ArrayList<Date>();

        calendar.set(2015, Calendar.DECEMBER, 13, 10, 0);
        eventTwoDates.add(calendar.getTime());

        calendar.set(2015, Calendar.DECEMBER, 13, 16, 0);
        eventTwoDates.add(calendar.getTime());

        eventService.create("Discussion", eventTwoDates, eventTwoDates, 25000, Rating.HIGH);
    }
}
