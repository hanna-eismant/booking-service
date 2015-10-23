package com.epam.spring.core;

import com.epam.spring.core.auditoriums.Auditorium;
import com.epam.spring.core.auditoriums.services.AuditoriumService;
import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.Rating;
import com.epam.spring.core.events.services.EventService;
import com.epam.spring.core.users.User;
import com.epam.spring.core.users.services.UserService;
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

        // user
        calendar.set(2015, Calendar.DECEMBER, 15, 14, 15);
        User hanna = userService.register("Hanna", "Hanna@Mail", calendar.getTime());

        // event one
        Event eventOne = eventService.create("Terminator 8", 5000, Rating.HIGH);

        // event two
        Event eventTwo = eventService.create("Just Married", 25000, Rating.LOW);

        // auditoriums
        AuditoriumService auditoriumService = context.getBean(AuditoriumService.class);
        List<Auditorium> auditoriums = auditoriumService.getAuditoriums();

        // assign
        calendar.set(2015, Calendar.DECEMBER, 15, 14, 0);
        eventService.assignAuditorium(eventOne, auditoriums.get(0), calendar.getTime());

        calendar.set(2015, Calendar.DECEMBER, 15, 23, 15);
        eventService.assignAuditorium(eventOne, auditoriums.get(0), calendar.getTime());

        calendar.set(2015, Calendar.DECEMBER, 15, 14, 0);
        eventService.assignAuditorium(eventTwo, auditoriums.get(0), calendar.getTime());



//        LocaleDate

    }
}
