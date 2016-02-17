package com.epam.spring.core.events;

import com.epam.spring.core.auditoriums.Auditorium;
import com.epam.spring.core.auditoriums.AuditoriumService;
import com.epam.spring.core.shared.DuplicateException;
import com.epam.spring.core.shared.NotFoundException;
import com.epam.spring.core.tickets.Ticket;
import com.epam.spring.core.tickets.TicketService;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("eventService")
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private AuditoriumService auditoriumService;

    @Autowired
    private TicketService ticketService;

    @Override
    public Event create(String name, Double basePrice, Rating rating)
            throws IllegalArgumentException, DuplicateException {

        // check event name
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Event name cannot be empty or 'null'");
        }
        if (eventRepository.existsByName(name)) {
            throw new DuplicateException("Event with name '" + name + "' already exist");
        }

        // check base price
        if (basePrice == null || basePrice.equals(0.0)) {
            throw new IllegalArgumentException("Event base price cannot be zero or 'null'");
        }

        // check rating
        if (rating == null) {
            throw new IllegalArgumentException("Event rating cannot be 'null'");
        }

        EventEntity eventEntity = new EventEntity(name, basePrice, rating);
        EventEntity save = eventRepository.save(eventEntity);

        Event result = new Event(eventEntity.getName(), eventEntity.getBasePrice(), eventEntity.getRating());
        result.setId(save.getId());
        return result;
    }

    @Override
    public Event getById(Long id) throws NotFoundException {
        // check id
        if (id == null) {
            throw new IllegalArgumentException("Id for search cannot be 'null'");
        }

        EventEntity eventEntity = eventRepository.findOne(id);

        if (eventEntity == null) {
            throw new NotFoundException("Event with id '" + id + "' doesn't exist");
        }

        // todo: map show list

        Event result = new Event(eventEntity.getName(), eventEntity.getBasePrice(), eventEntity.getRating());
        result.setId(eventEntity.getId());
        return result;
    }

    @Override
    public Show getShowById(final Long showId) throws NotFoundException {
        // check id
        if (showId == null) {
            throw new IllegalArgumentException("Id for search cannot be 'null'");
        }

        ShowEntity showEntity = showRepository.findOne(showId);

        if (showEntity == null) {
            throw new NotFoundException("Show with id '" + showId + "' doesn't exist");
        }

        Event event = new Event(showEntity.getEvent().getName(), showEntity.getEvent().getBasePrice(), showEntity.getEvent().getRating());
        event.setId(showEntity.getEvent().getId());
        Show show = new Show(event, showEntity.getDate(), 0);
        show.setAuditorium(auditoriumService.getAuditorium(showEntity.getAuditorium()));
        show.setId(showEntity.getId());
        return show;
    }

    @Override
    public List<Event> getAll() {
        Iterable<EventEntity> allEntities = eventRepository.findAll();
        List<Event> result = new ArrayList<>();

        for (EventEntity eventEntity : allEntities) {
            Event event = new Event(eventEntity.getName(), eventEntity.getBasePrice(), eventEntity.getRating());
            event.setId(eventEntity.getId());
            result.add(event);
        }

        return result;
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
