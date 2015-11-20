package com.epam.spring.core.auditoriums;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.tickets.Ticket;
import org.joda.time.LocalDateTime;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public abstract class TestUtils {

    public static void checkFreeTickets(List<Ticket> tickets, Event event, LocalDateTime date) {
        for (Ticket ticket : tickets) {
            assertNotNull("Result list should not contains 'null'", ticket);
            assertEquals("Result list has ticket with incorrect event", event, ticket.event);
            assertEquals("Result list has ticket with incorrect date", date, ticket.date);
            assertNull("Free ticket cannot contain user", ticket.user);
            assertNull("Free ticket cannot contain discount price", ticket.discountPrice);
        }
    }
}
