package com.epam.spring.core.events.services;

import com.epam.spring.core.auditoriums.Auditorium;
import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.Rating;
import com.epam.spring.core.events.dao.EventDAO;
import com.epam.spring.core.tickets.Ticket;
import com.epam.spring.core.tickets.dao.TicketDAO;
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
    private TicketDAO ticketDAO;

    @Autowired
    private Provider<Event> eventProvider;

    @Override
    public Event create(String name, Double basePrice, Rating rating) {
        Event event = eventProvider.get();
        event.name = name;
        event.basePrice = basePrice;

        if (Rating.HIGH.equals(rating)) {
            event.basePrice *= 1.2;
        }

        event.rating = rating;
        event = eventDAO.create(event);

        return event;
    }

    @Override
    public void remove(Event event) {
        eventDAO.remove(event);
    }

    @Override
    public Event getById(Long id) {
        return eventDAO.findById(id);
    }

    @Override
    public List<Event> getAll() {
        return eventDAO.findAll();
    }

    @Override
    public List<Ticket> assignAuditorium(Event event, Auditorium auditorium, LocalDateTime date) {
        List<Ticket> tickets = new ArrayList<>();

        for (int seat = 0; seat < auditorium.seats; seat++) {
            Ticket ticket = new Ticket(date, event, seat, auditorium.getVipSeats().contains(seat), event.basePrice);
            // maybe save all tickets at once
            ticket = ticketDAO.create(ticket);
            tickets.add(ticket);
        }

        return tickets;
    }

    public void setEventDAO(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    public void setTicketDAO(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }
}
