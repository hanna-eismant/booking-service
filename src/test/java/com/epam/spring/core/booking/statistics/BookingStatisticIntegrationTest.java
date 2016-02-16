package com.epam.spring.core.booking.statistics;

import com.epam.spring.core.AbstractStatisticIntegrationTest;
import com.epam.spring.core.events.Event;
import org.junit.Test;

import static com.epam.spring.core.TestConstants.HOBBIT_EVENT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BookingStatisticIntegrationTest extends AbstractStatisticIntegrationTest {

    @Test
    public void testCountBooked() {
        Long bookedStatistic = bookingStatistic.getBookedStatistic(HOBBIT_EVENT);
        assertNotNull("Booked Statistic for event with booked tickets cannot be null", bookedStatistic);
        assertEquals("Booked Statistic for event with booked tickets is incorrect", totalBookedTickets, bookedStatistic);

        Event absenteeEvent = new Event();
        absenteeEvent.setName("Absentee event");

        Long notBookedStatistic = bookingStatistic.getBookedStatistic(absenteeEvent);
        assertNotNull("Booked Statistic for event without booked tickets cannot be null", notBookedStatistic);
        assertEquals("Booked Statistic for event without booked tickets should be zero", Long.valueOf(0L), notBookedStatistic);
    }

    @Test
    public void testCountPriceQueried() {
        Long priceQueriedStatistic = bookingStatistic.getPriceQueriedStatistic(HOBBIT_EVENT);
        assertNotNull("Price Queried Statistic for event with booked tickets cannot be null", priceQueriedStatistic);
        assertEquals("Price Queried Statistic for event with booked tickets is incorrect", totalBookedTickets, priceQueriedStatistic);

        Event absenteeEvent = new Event();
        absenteeEvent.setName("Absentee event");

        Long notPriceQueriedStatistic = bookingStatistic.getPriceQueriedStatistic(absenteeEvent);
        assertNotNull("Price Queried Statistic for event without booked tickets cannot be null", notPriceQueriedStatistic);
        assertEquals("Price Queried Statistic for event without booked tickets should be zero", Long.valueOf(0L), notPriceQueriedStatistic);
    }
}
