package com.epam.spring.core.events;

import com.epam.spring.core.auditoriums.Auditorium;
import com.epam.spring.core.shared.NotFoundException;
import com.epam.spring.core.tickets.Ticket;
import com.epam.spring.core.tickets.TicketService;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Provider;
import java.util.ArrayList;
import java.util.List;

@Service("eventService")
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDAO eventDAO;

    @Autowired
    private ShowDAO showDAO;

    @Autowired
    private Provider<Event> eventProvider;

    @Autowired
    private TicketService ticketService;

    @Override
    public Event create(String name, Double basePrice, Rating rating) {
        Event event = eventProvider.get();
        event.setName(name);
        event.setBasePrice(basePrice);
        event.setRating(rating);
        event = eventDAO.create(event);

        return event;
    }

    @Override
    public void remove(Event event) {
        eventDAO.remove(event);
    }

    @Override
    public Event getById(Long id) throws NotFoundException {
        Event event = eventDAO.findById(id);

        if (event == null) {
            throw new NotFoundException("Event doesn't exist");
        }

        List<Show> shows = showDAO.getByEvent(event.getId());
        event.getShows().addAll(shows);
        return event;
    }

    @Override
    public Show getShow(final Long showId) {
        Show show = showDAO.findById(showId);
        Event event = eventDAO.findByShow(showId);
        show.setEvent(event);
        return show;
    }

    @Override
    public List<Event> getAll() {
        return eventDAO.findAll();
    }

    @Override
    public List<Ticket> assignAuditorium(Event event, Auditorium auditorium, LocalDateTime date) {
        List<Ticket> tickets = new ArrayList<>();

        for (int seat = 0; seat < auditorium.getSeats(); seat++) {
            Double ticketPrice =
                    ticketService.getTicketPrice(event, date, seat, auditorium.getVipSeats().contains(seat), null);
            // todo: change after refactor data model
            // Ticket ticket = new Ticket(date, event, seat, auditorium.getVipSeats().contains(seat), ticketPrice);
            // todo: save all tickets at once
//            ticket = ticketDAO.create(ticket);
//            tickets.add(ticket);
        }

        return tickets;
    }
}
