package com.epam.spring.core.booking.statistics;

import com.epam.spring.core.AbstractStatisticIntegrationTest;
import com.epam.spring.core.events.Event;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookingStatisticIntegrationTest extends AbstractStatisticIntegrationTest {

    @Test
    public void testCountBooked() {
        Integer bookedStatistic = bookingStatistic.getBookedStatistic(event);
        assertNotNull(bookedStatistic);
        assertEquals(totalBookedTickets, bookedStatistic);

        Integer notBookedStatistic = bookingStatistic.getBookedStatistic(new Event());
        assertNull(notBookedStatistic);
    }

    @Test
    public void testCountPriceQueried() {
        Integer priceQueriedStatistic = bookingStatistic.getPriceQueriedStatistic(event);
        assertNotNull("Price Queried Statistic for event with booked tickets cannot be null", priceQueriedStatistic);
        assertEquals(totalBookedTickets, priceQueriedStatistic);

        Integer notPriceQueriedStatistic = bookingStatistic.getPriceQueriedStatistic(new Event());
        assertNull(notPriceQueriedStatistic);
    }
}
