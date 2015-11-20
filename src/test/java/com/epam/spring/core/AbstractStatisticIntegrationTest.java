package com.epam.spring.core;

import com.epam.spring.core.booking.BookingService;
import com.epam.spring.core.booking.statistics.BookingStatistic;
import com.epam.spring.core.discounts.statistics.DiscountStatistic;
import com.epam.spring.core.tickets.Ticket;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.epam.spring.core.TestUtils.*;

public abstract class AbstractStatisticIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    protected BookingService bookingService;

    @Autowired
    protected BookingStatistic bookingStatistic;

    @Autowired
    protected DiscountStatistic discountStatistic;

    protected Integer jhonBookedTickets = 5;
    protected Integer janeBookedTickets = 3;
    protected Integer totalBookedTickets;

    @Before
    public void setUp() throws Exception {
        totalBookedTickets = jhonBookedTickets + janeBookedTickets;

        List<Ticket> tickets = bookingService.getFreeTicketsForEvent(EVENT_HOBBIT, TIME_HOBBIT_ONE);

        // book tickets
        for (int i = 1; i < jhonBookedTickets + 1; i++) {
            bookingService.bookTicket(USER_JHON, tickets.get(i));
        }

        for (int i = 10; i < janeBookedTickets + 10; i++) {
            bookingService.bookTicket(USER_JANE, tickets.get(i));
        }
    }

    @After
    public void tearDown() {
        discountStatistic.removeAll();
        bookingStatistic.removeAll();
    }
}
