package com.epam.spring.core.booking;

import com.epam.spring.core.AbstractIntegrationTest;
import com.epam.spring.core.tickets.Ticket;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.epam.spring.core.TestConstants.*;
import static com.epam.spring.core.TestUtils.checkFreeTickets;
import static org.junit.Assert.*;

public class BookingServiceIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private BookingService bookingService;

    @Test
    public void testBookTicket() {
        Ticket savedTicket = bookingService.bookTicket(USER_JANE, TICKET);

        assertNotNull("Updated ticket should not be 'null'", savedTicket);

        assertEquals("Updated ticket has incorrect id", TICKET.id, savedTicket.id);
        assertEquals("Updated ticket has incorrect event", TICKET.event, savedTicket.event);
        assertEquals("Updated ticket has incorrect date", TICKET.date, savedTicket.date);
        assertEquals("Updated ticket has incorrect seat number", TICKET.seat, savedTicket.seat);
        assertEquals("Updated ticket has incorrect base price", TICKET.basePrice, savedTicket.basePrice);
        assertEquals("Updated ticket has incorrect VIP flag", TICKET.isVip, savedTicket.isVip);
        assertEquals("Updated ticket has incorrect discount price", TICKET.discountPrice, savedTicket.discountPrice);
        assertEquals("Updated ticket has incorrect user", TICKET.user, savedTicket.user);

        TICKET.user = null;
        TICKET.discountPrice = null;
    }

    @Test
    public void testGetTicketsForEvent() {
        List<Ticket> gamerTickets = bookingService.getTicketsForEvent(GAMER_EVENT, GAMER_TIME);
        assertNotNull("Found ticket list should not be 'null'", gamerTickets);
        assertEquals("Found ticket list have incorrect size", GAMER_TOTAL_TICKETS, gamerTickets.size());

        for (Ticket ticket : gamerTickets) {
            assertNotNull("Result list should not contains 'null'", ticket);
            assertEquals("Result list has ticket with incorrect event", GAMER_EVENT, ticket.event);
            assertEquals("Result list has ticket with incorrect date", GAMER_TIME, ticket.date);
        }

        List<Ticket> hobbitTickets = bookingService.getTicketsForEvent(HOBBIT_EVENT, HOBBIT_TIME_ONE);
        assertNotNull("Found ticket list should not be 'null'", hobbitTickets);
        assertEquals("Found ticket list have incorrect size", HOBBIT_TOTAL_TICKETS_ONE, hobbitTickets.size());

        for (Ticket ticket : hobbitTickets) {
            assertNotNull("Result list should not contains 'null'", ticket);
            assertEquals("Result list has ticket with incorrect event", HOBBIT_EVENT, ticket.event);
            assertEquals("Result list has ticket with incorrect date", HOBBIT_TIME_ONE, ticket.date);
        }
    }

    @Test
    public void testGetFreeTicketsForEvent() {
        List<Ticket> gamerTickets = bookingService.getFreeTicketsForEvent(GAMER_EVENT, GAMER_TIME);
        assertNotNull("Found ticket list should not be 'null'", gamerTickets);
        assertEquals("Found ticket list have incorrect size", GAMER_FREE_TICKETS, gamerTickets.size());

        checkFreeTickets(gamerTickets, GAMER_EVENT, GAMER_TIME);

        List<Ticket> hobbitTickets = bookingService.getFreeTicketsForEvent(HOBBIT_EVENT, HOBBIT_TIME_ONE);
        assertNotNull("Found ticket list should not be 'null'", hobbitTickets);
        assertEquals("Found ticket list have incorrect size", HOBBIT_FREE_TICKETS_ONE, hobbitTickets.size());

        checkFreeTickets(hobbitTickets, HOBBIT_EVENT, HOBBIT_TIME_ONE);
    }
}
