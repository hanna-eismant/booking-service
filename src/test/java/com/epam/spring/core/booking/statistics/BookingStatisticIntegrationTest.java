package com.epam.spring.core.booking.statistics;

import com.epam.spring.core.AbstractStatisticIntegrationTest;
import com.epam.spring.core.events.Event;
import org.junit.Test;

import static com.epam.spring.core.TestUtils.*;
import static org.junit.Assert.*;

public class BookingStatisticIntegrationTest extends AbstractStatisticIntegrationTest {

    @Test
    public void testCountBooked() {
        Integer bookedStatistic = bookingStatistic.getBookedStatistic(HOBBIT_EVENT);
        assertNotNull("Booked Statistic for event with booked tickets cannot be null", bookedStatistic);
        assertEquals("Booked Statistic for event with booked tickets is incorrect", totalBookedTickets, bookedStatistic);

        Integer notBookedStatistic = bookingStatistic.getBookedStatistic(new Event());
        assertNull("Booked Statistic for event without booked tickets should be null", notBookedStatistic);
    }

    @Test
    public void testCountPriceQueried() {
        Integer priceQueriedStatistic = bookingStatistic.getPriceQueriedStatistic(HOBBIT_EVENT);
        assertNotNull("Price Queried Statistic for event with booked tickets cannot be null", priceQueriedStatistic);
        assertEquals("Price Queried Statistic for event with booked tickets is incorrect", totalBookedTickets, priceQueriedStatistic);

        Integer notPriceQueriedStatistic = bookingStatistic.getPriceQueriedStatistic(new Event());
        assertNull("Price Queried Statistic for event without booked tickets should be null", notPriceQueriedStatistic);
    }
}
