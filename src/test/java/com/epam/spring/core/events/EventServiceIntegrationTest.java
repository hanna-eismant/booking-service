package com.epam.spring.core.events;

import com.epam.spring.core.AbstractIntegrationTest;
import com.epam.spring.core.shared.DuplicateException;
import com.epam.spring.core.shared.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.epam.spring.core.TestConstants.BASE_PRICE;
import static com.epam.spring.core.TestConstants.EVENT_NAME;
import static com.epam.spring.core.TestConstants.HOBBIT_EVENT;
import static com.epam.spring.core.TestConstants.HOBBIT_SHOW_ONE;
import static com.epam.spring.core.TestConstants.RATING;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EventServiceIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private EventService eventService;

    @Test
    public void testCreate() throws DuplicateException {
        Event event = eventService.create(EVENT_NAME, BASE_PRICE, RATING);
        assertNotNull("Created event cannot be null", event);
        assertNotNull("Created event should has id", event.getId());
        assertEquals("Created event has incorrect name", EVENT_NAME, event.getName());
        assertEquals("Created event has incorrect base price", BASE_PRICE, event.getBasePrice());
        assertEquals("Created event has incorrect rating", RATING, event.getRating());
    }

    @Test(expected = DuplicateException.class)
    public void testCreateDuplicate() throws DuplicateException {
        eventService.create(HOBBIT_EVENT.getName(), BASE_PRICE, RATING);
    }

    @Test
    public void testGetById() throws NotFoundException {
        Event event = eventService.getById(HOBBIT_EVENT.getId());
        assertNotNull("Found event cannot be null", event);
        assertEquals("Found event has incorrect id", HOBBIT_EVENT.getId(), event.getId());
        assertEquals("Found event has incorrect name", HOBBIT_EVENT.getName(), event.getName());
        assertEquals("Found event has incorrect base price", HOBBIT_EVENT.getBasePrice(), event.getBasePrice());
        assertEquals("Found event has incorrect rating", HOBBIT_EVENT.getRating(), event.getRating());
    }

    @Test(expected = NotFoundException.class)
    public void testGetByIdNonExistent() throws NotFoundException {
        eventService.getById(1_000L);
    }

    @Test
    public void testGetAll() {
        List<Event> all = eventService.getAll();
        assertNotNull("Found list of events cannot be null", all);
        assertEquals("Found list of events has incorrect size", 9, all.size());
    }

    @Test
    public void testGetShowById() throws NotFoundException {
        Show show = eventService.getShowById(HOBBIT_SHOW_ONE.getId());
        assertNotNull("Found show cannot be null", show);
        assertEquals("Found show has incorrect id", HOBBIT_SHOW_ONE.getId(), show.getId());
        assertEquals("Found show has incorrect date", HOBBIT_SHOW_ONE.getDate(), show.getDate());
        assertEquals("Found show has incorrect auditorium", HOBBIT_SHOW_ONE.getAuditorium(), show.getAuditorium());
        assertEquals("Found show has incorrect event", HOBBIT_SHOW_ONE.getEvent(), show.getEvent());
        assertNotNull("Found show has 'null' tickets list", show.getTickets());
        assertEquals("Found show has incorrect total tickets count", HOBBIT_SHOW_ONE.getTickets().size(), show.getTickets().size());
        assertEquals("Found show has incorrect free tickets count", HOBBIT_SHOW_ONE.getFreeTicketCount(), show.getFreeTicketCount());
    }
}
