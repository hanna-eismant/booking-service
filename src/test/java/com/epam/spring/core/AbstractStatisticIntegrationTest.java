package com.epam.spring.core;

import com.epam.spring.core.auditoriums.Auditorium;
import com.epam.spring.core.auditoriums.services.AuditoriumService;
import com.epam.spring.core.booking.BookingService;
import com.epam.spring.core.booking.statistics.BookingStatistic;
import com.epam.spring.core.discounts.statistics.DiscountStatistic;
import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.dao.EventDAO;
import com.epam.spring.core.events.services.EventService;
import com.epam.spring.core.users.User;
import com.epam.spring.core.users.dao.UserDAO;
import com.epam.spring.core.users.services.UserService;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.epam.spring.core.TestUtils.*;

public abstract class AbstractStatisticIntegrationTest extends AbstractIntegrationTest {
    @Autowired
    protected UserDAO userDAO;

    @Autowired
    protected EventDAO eventDAO;

    @Autowired
    protected UserService userService;

    @Autowired
    protected EventService eventService;

    @Autowired
    protected AuditoriumService auditoriumService;

    @Autowired
    protected BookingService bookingService;

    @Autowired
    protected BookingStatistic bookingStatistic;

    @Autowired
    protected DiscountStatistic discountStatistic;

    protected User user;
    protected User admin;
    protected Event event;

    protected Integer userBookedTickets = 5;
    protected Integer adminBookedTickets = 3;
    protected Integer totalBookedTickets;

    @Before
    public void setUp() throws Exception {
        List<Auditorium> auditoriums = auditoriumService.getAuditoriums();

        totalBookedTickets = userBookedTickets + adminBookedTickets;

        // create user
        user = userService.register(USER_NAME, USER_EMAIL, USER_BIRTHDAY);
        admin = userService.register(ADMIN_NAME, ADMIN_EMAIL, ADMIN_BIRTHDAY);

        // create event
        event = eventService.create(EVENT_NAME, BASE_PRICE, RATING);

        // assign event
        eventService.assignAuditorium(event, auditoriums.get(0), EVENT_DATE);

        // book tickets
        for (int i = 1; i < userBookedTickets + 1; i++) {
            bookingService.bookTicket(user, event.getTickets().get(i));
        }

        for (int i = 10; i < adminBookedTickets + 10; i++) {
            bookingService.bookTicket(admin, event.getTickets().get(i));
        }
    }

    @After
    public void tearDown() {
        userDAO.removeAll();
        eventDAO.removeAll();
        discountStatistic.removeAll();
        bookingStatistic.removeAll();
    }
}
