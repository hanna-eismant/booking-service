package com.epam.spring.core;

import com.epam.spring.core.booking.BookingService;
import com.epam.spring.core.booking.statistics.BookingStatistic;
import com.epam.spring.core.discounts.statistics.DiscountStatistic;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractStatisticIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    protected BookingService bookingService;

    @Autowired
    protected BookingStatistic bookingStatistic;

    @Autowired
    protected DiscountStatistic discountStatistic;

    protected Long jhonBookedTickets = 5L;
    protected Long janeBookedTickets = 3L;
    protected Long totalBookedTickets;

    @Before
    public void setUp() throws Exception {
//        totalBookedTickets = jhonBookedTickets + janeBookedTickets;
//
//        List<Ticket> tickets = bookingService.getFreeTicketsForEvent(HOBBIT_EVENT, HOBBIT_TIME_ONE);
//
//        // book tickets
//        for (int i = 1; i < jhonBookedTickets + 1; i++) {
//            bookingService.bookTicket(USER_JHON, tickets.get(i));
//        }
//
//        for (int i = 10; i < janeBookedTickets + 10; i++) {
//            bookingService.bookTicket(USER_JANE, tickets.get(i));
//        }
    }
}
