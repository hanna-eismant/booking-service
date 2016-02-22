package com.epam.spring.core.tickets;

import com.epam.spring.core.AbstractIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TicketServiceIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private TicketService ticketService;

    // todo: write after refactor service's interface

    @Test
    public void testGetTicketPrice() {
//        /*
//        base for event - 8_000
//        base fir event with rating - 9_600 (+20%)
//        vip - no
//        10th - no
//        birthday - no
//
//        result - 9_600
//         */
//        Double ticketPriceOne = ticketService.getTicketPrice(HOBBIT_EVENT, HOBBIT_TIME_ONE, 4, false, USER_JANE);
//        assertNotNull("Calculated ticket price cannot be 'null'", ticketPriceOne);
//        assertEquals(Double.valueOf(9_600.0d), ticketPriceOne);
//
//        /*
//        base for event - 8_000
//        base fir event with rating - 9_600 (+20%)
//        vip - yes - 19_200
//        10th - no
//        birthday - yes (-5%)
//
//        result - 18_240
//         */
//        Double ticketPriceTwo = ticketService.getTicketPrice(HOBBIT_EVENT, GAMER_TIME, 2, true, USER_JANE);
//        assertNotNull("Calculated ticket price cannot be 'null'", ticketPriceTwo);
//        assertEquals(Double.valueOf(18_240.0d), ticketPriceTwo);
    }
}
