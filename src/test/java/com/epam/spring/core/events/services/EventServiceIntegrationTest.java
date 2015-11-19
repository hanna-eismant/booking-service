package com.epam.spring.core.events.services;

import com.epam.spring.core.AbstractIntegrationTest;
import com.epam.spring.core.events.Event;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.epam.spring.core.TestUtils.*;
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
        assertEquals(BASE_PRICE, event.basePrice);
        assertEquals(RATING, event.rating);
        assertNotNull(event.getTickets());
        assertTrue(event.getTickets().isEmpty());
    }

    @Test
    public void testRemove() {
        int oldSize = eventService.getAll().size();

        eventService.remove(EVENT_REMOVE);

        Event event = eventService.getById(EVENT_REMOVE.id);
        int newSize = eventService.getAll().size();

        assertNull(event);
        assertEquals(--oldSize, newSize);
    }

    @Test
    public void testGetById() {
        Event event = eventService.getById(EVENT_HOBBIT.id);

        assertNotNull(event);
        assertEquals(EVENT_HOBBIT.id, event.id);
        assertEquals(EVENT_HOBBIT.name, event.name);
        assertEquals(EVENT_HOBBIT.basePrice, event.basePrice);
        assertEquals(EVENT_HOBBIT.rating, event.rating);

        // todo: test tickets
    }

    @Test
    public void testGetAll() {
        List<Event> allEvents = eventService.getAll();

        assertNotNull(allEvents);
        // 2 events from sql
        // maybe has 1 from create test
        // maybe has not 1 from remove test
        assertTrue(allEvents.size() >= 2);
    }
}
