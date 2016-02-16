package com.epam.spring.core.events;

import com.epam.spring.core.AbstractIntegrationTest;
import com.epam.spring.core.auditoriums.Auditorium;
import com.epam.spring.core.shared.NotFoundException;
import com.epam.spring.core.tickets.Ticket;
import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.epam.spring.core.TestConstants.AUDITORIUM_ONE_NAME;
import static com.epam.spring.core.TestConstants.AUDITORIUM_ONE_SEATS;
import static com.epam.spring.core.TestConstants.AUDITORIUM_ONE_VIPSEATS;
import static com.epam.spring.core.TestConstants.BASE_PRICE;
import static com.epam.spring.core.TestConstants.EVENT_NAME;
import static com.epam.spring.core.TestConstants.GAMER_EVENT;
import static com.epam.spring.core.TestConstants.HOBBIT_EVENT;
import static com.epam.spring.core.TestConstants.RATING;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class EventServiceIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private EventService eventService;

    @Test
    public void testCreate() throws Exception {
        Event event = eventService.create(EVENT_NAME, BASE_PRICE, RATING);

        assertNotNull(event);
        assertNotNull(event.getId());
        assertEquals(EVENT_NAME, event.getName());
        assertEquals(BASE_PRICE, event.getBasePrice());
        assertEquals(RATING, event.getRating());
    }

    @Test
    public void testRemove() throws NotFoundException {
        int oldSize = eventService.getAll().size();

        eventService.remove(GAMER_EVENT);

        Event event = eventService.getById(GAMER_EVENT.getId());
        int newSize = eventService.getAll().size();

        assertNull(event);
        assertEquals(--oldSize, newSize);
    }

    @Test
    public void testGetById() throws NotFoundException {
        Event event = eventService.getById(HOBBIT_EVENT.getId());

        assertNotNull(event);
        assertEquals(HOBBIT_EVENT.getId(), event.getId());
        assertEquals(HOBBIT_EVENT.getName(), event.getName());
        assertEquals(HOBBIT_EVENT.getBasePrice(), event.getBasePrice());
        assertEquals(HOBBIT_EVENT.getRating(), event.getRating());
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
        auditorium.setName(AUDITORIUM_ONE_NAME);
        auditorium.setSeats(AUDITORIUM_ONE_SEATS);
        auditorium.getVipSeats().addAll(AUDITORIUM_ONE_VIPSEATS);

        LocalDateTime eventDate = LocalDateTime.parse("2015-03-03T15:30:00.000");

        List<Ticket> tickets = eventService.assignAuditorium(HOBBIT_EVENT, auditorium, eventDate);

        assertNotNull("Created ticket list cannot be 'null'", tickets);
        assertEquals("Created ticket list should have same size like seats count at assigned auditorium",
                auditorium.getSeats().longValue(), tickets.size());

        for (Ticket ticket : tickets) {
            assertNotNull("Created ticket list cannot contain 'null'", ticket);
            assertNotNull("Created ticket should have id", ticket.getId());
            assertNotNull("Created ticket should have price", ticket.getBasePrice());
            assertNotEquals("Created ticket have incorrect price", 0.0d, ticket.getBasePrice());
            assertNotNull("Created ticket should have seat number", ticket.getSeat());
            assertNull("Created ticket cannot contain discount price", ticket.getDiscountPrice());
            assertNull("Created ticket cannot contain user", ticket.getUser());
//            assertEquals("Created ticket contain incorrect event", HOBBIT_EVENT, ticket.event);
//            assertEquals("Created ticket contain incorrect date", eventDate, ticket.date);
        }
    }
}
