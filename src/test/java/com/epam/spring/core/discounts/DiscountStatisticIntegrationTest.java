package com.epam.spring.core.discounts;

import com.epam.spring.core.AbstractIntegrationTest;
import com.epam.spring.core.auditoriums.Auditorium;
import com.epam.spring.core.auditoriums.services.AuditoriumService;
import com.epam.spring.core.booking.BookingService;
import com.epam.spring.core.discounts.statistics.DiscountStatistic;
import com.epam.spring.core.discounts.strategies.DiscountStrategy;
import com.epam.spring.core.discounts.strategies.DiscountStrategyBirthdayImpl;
import com.epam.spring.core.discounts.strategies.DiscountStrategyTenTicketImpl;
import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.dao.EventDAO;
import com.epam.spring.core.events.services.EventService;
import com.epam.spring.core.users.User;
import com.epam.spring.core.users.dao.UserDAO;
import com.epam.spring.core.users.services.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.epam.spring.core.TestUtils.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class DiscountStatisticIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private EventDAO eventDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private AuditoriumService auditoriumService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private DiscountStatistic discountStatistic;

    private User user;
    private User admin;

    private Integer userBookedTickets = 5;
    private Integer adminBookedTickets = 3;
    private Integer totalBookedTickets;

    @Before
    public void setUp() throws Exception {
        List<Auditorium> auditoriums = auditoriumService.getAuditoriums();

        totalBookedTickets = userBookedTickets + adminBookedTickets;

        // create user
        user = userService.register(USER_NAME, USER_EMAIL, USER_BIRTHDAY);
        admin = userService.register(ADMIN_NAME, ADMIN_EMAIL, ADMIN_BIRTHDAY);

        // create event
        Event event = eventService.create(EVENT_NAME, BASE_PRICE, RATING);

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
    }

    @Test
    public void testCountTotal() {
        Integer discountBirthdayStatistic = discountStatistic.getTotalStatistic(DiscountStrategyBirthdayImpl.class);
        assertNotNull(discountBirthdayStatistic);
        assertEquals(totalBookedTickets, discountBirthdayStatistic);

        Integer discountTenTicketStatistic = discountStatistic.getTotalStatistic(DiscountStrategyTenTicketImpl.class);
        assertNotNull(discountTenTicketStatistic);
        assertEquals(totalBookedTickets, discountTenTicketStatistic);

        Integer unknownDiscountStatistic = discountStatistic.getTotalStatistic(DiscountStrategy.class);
        assertNull(unknownDiscountStatistic);
    }

    @Test
    public void testCountTenTicket() {
        Integer userStatistic = discountStatistic.getTenTicketStatistic(user);
        assertNotNull(userStatistic);
        assertEquals(userBookedTickets, userStatistic);

        Integer adminStatistic = discountStatistic.getTenTicketStatistic(admin);
        assertNotNull(adminStatistic);
        assertEquals(adminBookedTickets, adminStatistic);

        Integer unknownUserStatistic = discountStatistic.getTenTicketStatistic(new User());
        assertNull(unknownUserStatistic);
    }

    @Test
    public void testCountBirthday() {
        Integer userStatistic = discountStatistic.getBirthdayStatistic(user);
        assertNotNull(userStatistic);
        assertEquals(userBookedTickets, userStatistic);

        Integer adminStatistic = discountStatistic.getBirthdayStatistic(admin);
        assertNotNull(adminStatistic);
        assertEquals(adminBookedTickets, adminStatistic);

        Integer unknownUserStatistic = discountStatistic.getBirthdayStatistic(new User());
        assertNull(unknownUserStatistic);
    }
}
