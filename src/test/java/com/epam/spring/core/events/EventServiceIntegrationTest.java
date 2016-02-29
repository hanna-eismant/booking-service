package com.epam.spring.core.events;

import com.epam.spring.core.AbstractIntegrationTest;
import com.epam.spring.core.shared.DuplicateException;
import com.epam.spring.core.shared.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.epam.spring.core.TestConstants.BASE_PRICE;
import static com.epam.spring.core.TestConstants.EVENT_NAME;
import static com.epam.spring.core.TestConstants.GAMER_EVENT;
import static com.epam.spring.core.TestConstants.HOBBIT_EVENT;
import static com.epam.spring.core.TestConstants.HOBBIT_SHOW_ONE;
import static com.epam.spring.core.TestConstants.HOBBIT_TOTAL_TICKETS_ONE;
import static com.epam.spring.core.TestConstants.RATING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class EventServiceIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private EventService eventService;

    @Test
    public void testCreate() throws DuplicateException {
        Event event = eventService.create(EVENT_NAME, BASE_PRICE, RATING);

        assertThat(event).as("Created event cannot be null").isNotNull();
        assertThat(event).as("Created event should has id").isNotNull();

        assertThat(event.getName()).as("Created event has incorrect name").isEqualTo(EVENT_NAME);
        assertThat(event.getBasePrice()).as("Created event has incorrect base price").isEqualTo(BASE_PRICE);
        assertThat(event.getRating()).as("Created event has incorrect rating").isEqualTo(RATING);

        assertThat(event.getShows()).as("Show's list for created event cannot be 'null'").isNotNull();
        assertThat(event.getShows()).as("Created event cannot has any shows").isEmpty();
    }

    @Test
    public void testCreateDuplicate() {
        assertThatExceptionOfType(DuplicateException.class).isThrownBy(() -> eventService.create(HOBBIT_EVENT.getName(), BASE_PRICE, RATING));
    }

    @Test
    public void testGetById() throws NotFoundException {
        Event event = eventService.getById(HOBBIT_EVENT.getId());

        assertThat(event).as("Found event cannot be null").isNotNull();

        assertThat(event.getId()).as("Found event has incorrect id").isEqualTo(HOBBIT_EVENT.getId());
        assertThat(event.getName()).as("Found event has incorrect name").isEqualTo(HOBBIT_EVENT.getName());
        assertThat(event.getBasePrice()).as("Found event has incorrect base price").isEqualTo(HOBBIT_EVENT.getBasePrice());
        assertThat(event.getRating()).as("Found event has incorrect rating").isEqualTo(HOBBIT_EVENT.getRating());

        assertThat(event.getShows()).as("Show's list for found event cannot be 'null'").isNotNull();
        assertThat(event.getShows()).as("Show's list for found event has incorrect size").hasSize(2);
    }

    @Test
    public void testGetByIdNonExistent() {
        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> eventService.getById(1_000L));
    }

    @Test
    public void testGetAll() {
        List<Event> all = eventService.getAll();

        assertThat(all).as("Found list of events cannot be null").isNotNull();
        assertThat(all).as("Found list of events has incorrect size").hasSize(9);
        assertThat(all).as("Found list of events has incorrect items").extracting("name").contains(HOBBIT_EVENT.getName(), GAMER_EVENT.getName());
    }

    @Test
    public void testGetShowById() throws NotFoundException {
        Show show = eventService.getShowById(HOBBIT_SHOW_ONE.getId());

        assertThat(show).as("Found show cannot be null").isNotNull();

        assertThat(show.getId()).as("Found show has incorrect id").isEqualTo(HOBBIT_SHOW_ONE.getId());
        assertThat(show.getDate()).as("Found show has incorrect date").isEqualTo(HOBBIT_SHOW_ONE.getDate());
        assertThat(show.getAuditorium()).as("Found show has incorrect auditorium").isEqualTo(HOBBIT_SHOW_ONE.getAuditorium());
        assertThat(show.getEvent()).as("Found show has incorrect event").isEqualTo(HOBBIT_SHOW_ONE.getEvent());
        assertThat(show.getFreeTicketCount()).as("Found show has incorrect free tickets count").isEqualTo(HOBBIT_SHOW_ONE.getFreeTicketCount());

        assertThat(show.getTickets()).as("Found show has 'null' tickets list").isNotNull();
        assertThat(show.getTickets()).as("Found show has incorrect total tickets count").hasSize(HOBBIT_TOTAL_TICKETS_ONE);
    }
}
