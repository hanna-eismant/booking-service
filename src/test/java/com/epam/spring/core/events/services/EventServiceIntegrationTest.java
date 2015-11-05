package com.epam.spring.core.events.services;

import com.epam.spring.core.AbstractIntegrationTest;
import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.dao.EventDAO;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.epam.spring.core.TestUtils.*;
import static org.junit.Assert.*;

public class EventServiceIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventDAO eventDAO;

    @After
    public void tearDown() {
        eventDAO.removeAll();
    }

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
}
