package com.epam.spring.core.events.services;

import com.epam.spring.core.AbstractIntegrationTest;
import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.Rating;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class EventServiceIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private EventService eventService;

    private String eventName;
    private Double basePrice;
    private Rating rating;

    @Before
    public void setUp() throws Exception {
        eventName = "Test event";
        basePrice = 10.0;
        rating = Rating.MID;
    }

    @Test
    public void testCreate() throws Exception {
        Event event = eventService.create(eventName, basePrice, rating);

        assertNotNull(event);
        assertNotNull(event.id);
        assertEquals(eventName, event.name);
        assertEquals(basePrice, event.basePrice);
        assertEquals(rating, event.rating);
        assertNotNull(event.getTickets());
        assertTrue(event.getTickets().isEmpty());
    }
}
