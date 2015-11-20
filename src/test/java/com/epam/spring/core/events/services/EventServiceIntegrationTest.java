package com.epam.spring.core.events.services;

import com.epam.spring.core.AbstractIntegrationTest;
import com.epam.spring.core.auditoriums.Auditorium;
import com.epam.spring.core.events.Event;
import com.epam.spring.core.tickets.Ticket;
import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.epam.spring.core.TestConstants.*;
import static org.junit.Assert.*;

public class EventServiceIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private EventService eventService;

    @Test
    public void testCreate() throws Exception {
        Event event = eventService.create(EVENT_NAME, BASE_PRICE, RATING);

        assertNotNull(event);
        assertNotNull(event.id);
        assertEquals(EVENT_NAME, event.name);
        assertEquals(BASE_PRICE_WITH_RATING, event.basePrice);
        assertEquals(RATING, event.rating);
    }

    @Test
    public void testRemove() {
        int oldSize = eventService.getAll().size();

        eventService.remove(GAMER_EVENT);

        Event event = eventService.getById(GAMER_EVENT.id);
        int newSize = eventService.getAll().size();

        assertNull(event);
        assertEquals(--oldSize, newSize);
    }

    @Test
    public void testGetById() {
        Event event = eventService.getById(HOBBIT_EVENT.id);

        assertNotNull(event);
        assertEquals(HOBBIT_EVENT.id, event.id);
        assertEquals(HOBBIT_EVENT.name, event.name);
        assertEquals(HOBBIT_EVENT.basePrice, event.basePrice);
        assertEquals(HOBBIT_EVENT.rating, event.rating);
    }

    @Test
    public void testGetAll() {
        List<Event> allEvents = eventService.getAll();

        assertNotNull(allEvents);
        assertEquals(allEvents.size(), 2);
    }

    @Test
    public void testAssignAuditorium() {
        Auditorium auditorium = new Auditorium();
        auditorium.name = AUDITORIUM_ONE_NAME;
        auditorium.seats = AUDITORIUM_ONE_SEATS;
        auditorium.getVipSeats().addAll(AUDITORIUM_ONE_VIPSEATS);

        LocalDateTime eventDate = LocalDateTime.parse("2015-03-03T15:30:00.000");

        List<Ticket> tickets = eventService.assignAuditorium(HOBBIT_EVENT, auditorium, eventDate);

        assertNotNull("Created ticket list cannot be 'null'", tickets);
        assertEquals("Created ticket list should have same size like seats count at assigned auditorium",
                auditorium.seats.longValue(), tickets.size());

        for (Ticket ticket : tickets) {
            assertNotNull("Created ticket list cannot contain 'null'", ticket);
            assertNotNull("Created ticket should have id", ticket.id);
            assertNotNull("Created ticket should have price", ticket.basePrice);
            assertNotEquals("Created ticket have incorrect price", 0.0d, ticket.basePrice);
            assertNotNull("Created ticket should have seat number", ticket.seat);
            assertNull("Created ticket cannot contain discount price", ticket.discountPrice);
            assertNull("Created ticket cannot contain user", ticket.user);
            assertEquals("Created ticket contain incorrect event", HOBBIT_EVENT, ticket.event);
            assertEquals("Created ticket contain incorrect date", eventDate, ticket.date);
        }
    }
}
