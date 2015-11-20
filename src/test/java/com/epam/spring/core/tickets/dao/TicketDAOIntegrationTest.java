package com.epam.spring.core.tickets.dao;

import com.epam.spring.core.AbstractIntegrationTest;
import com.epam.spring.core.tickets.Ticket;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.epam.spring.core.TestConstants.*;
import static com.epam.spring.core.TestUtils.checkFreeTickets;
import static org.junit.Assert.*;

public class TicketDAOIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    TicketDAO ticketDAO;

    @Test
    public void testCreate() {
        Ticket ticket = new Ticket(TICKET_DATE, HOBBIT_EVENT, TICKET_SEAT, false, TICKET_BASE_PRICE);
        Ticket savedTicket = ticketDAO.create(ticket);

        assertNotNull("Created ticket should not be 'null'", savedTicket);
        assertNotNull("Created ticket should have id", savedTicket.id);

        assertEquals("Created ticket has incorrect event", ticket.event, savedTicket.event);
        assertEquals("Created ticket has incorrect date", ticket.date, savedTicket.date);
        assertEquals("Created ticket has incorrect seat number", ticket.seat, savedTicket.seat);
        assertEquals("Created ticket has incorrect base price", ticket.basePrice, savedTicket.basePrice);

        assertFalse("Created ticket has incorrect VIP flag", savedTicket.isVip);

        assertNull("Created ticket should not have discount price", savedTicket.discountPrice);
        assertNull("Created ticket should not have user", savedTicket.user);
    }

    @Test
    public void testUpdate() {
        TICKET.user = USER_JANE;
        TICKET.discountPrice = TICKET.basePrice;

        Ticket savedTicket = ticketDAO.update(TICKET);

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
    public void testFindByEventAndDate() {
        List<Ticket> gamerTickets = ticketDAO.findByEventAndDate(GAMER_EVENT, GAMER_TIME);
        assertNotNull("Found ticket list should not be 'null'", gamerTickets);
        assertEquals("Found ticket list have incorrect size", GAMER_TOTAL_TICKETS, gamerTickets.size());

        for (Ticket ticket : gamerTickets) {
            assertNotNull("Result list should not contains 'null'", ticket);
            assertEquals("Result list has ticket with incorrect event", GAMER_EVENT, ticket.event);
            assertEquals("Result list has ticket with incorrect date", GAMER_TIME, ticket.date);
        }

        List<Ticket> hobbitTickets = ticketDAO.findByEventAndDate(HOBBIT_EVENT, HOBBIT_TIME_ONE);
        assertNotNull("Found ticket list should not be 'null'", hobbitTickets);
        assertEquals("Found ticket list have incorrect size", HOBBIT_TOTAL_TICKETS_ONE, hobbitTickets.size());

        for (Ticket ticket : hobbitTickets) {
            assertNotNull("Result list should not contains 'null'", ticket);
            assertEquals("Result list has ticket with incorrect event", HOBBIT_EVENT, ticket.event);
            assertEquals("Result list has ticket with incorrect date", HOBBIT_TIME_ONE, ticket.date);
        }
    }

    @Test
    public void testFindFreeByEventAndDate() {
        List<Ticket> gamerTickets = ticketDAO.findFreeByEventAndDate(GAMER_EVENT, GAMER_TIME);
        assertNotNull("Found ticket list should not be 'null'", gamerTickets);
        assertEquals("Found ticket list have incorrect size", GAMER_FREE_TICKETS, gamerTickets.size());

        checkFreeTickets(gamerTickets, GAMER_EVENT, GAMER_TIME);

        List<Ticket> hobbitTickets = ticketDAO.findFreeByEventAndDate(HOBBIT_EVENT, HOBBIT_TIME_ONE);
        assertNotNull("Found ticket list should not be 'null'", hobbitTickets);
        assertEquals("Found ticket list have incorrect size", HOBBIT_FREE_TICKETS_ONE, hobbitTickets.size());

        checkFreeTickets(hobbitTickets, HOBBIT_EVENT, HOBBIT_TIME_ONE);
    }

    @Test
    public void testFindByUser() {
        List<Ticket> jhonTickets = ticketDAO.findByUser(USER_JHON);
        assertNotNull("Found ticket list should not be 'null'", jhonTickets);
        assertEquals("Found ticket list have incorrect size", JHON_BOOKED_COUNT, jhonTickets.size());

        for (Ticket ticket : jhonTickets) {
            assertNotNull("Result list should not contains 'null'", ticket);
            assertEquals("Result list has incorrect ticket " + ticket, USER_JHON, ticket.user);
        }

        List<Ticket> janeTickets = ticketDAO.findByUser(USER_JANE);
        assertNotNull("Found ticket list should not be 'null'", janeTickets);
        assertEquals("Found ticket list have incorrect size", JANE_BOOKED_COUNT, janeTickets.size());

        for (Ticket ticket : janeTickets) {
            assertNotNull("Result list should not contains 'null'", ticket);
            assertEquals("Result list has incorrect ticket " + ticket, USER_JANE, ticket.user);
        }
    }
}
