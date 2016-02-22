package com.epam.spring.core.events;

import com.epam.spring.core.auditoriums.Auditorium;
import com.epam.spring.core.auditoriums.AuditoriumService;
import com.epam.spring.core.shared.DuplicateException;
import com.epam.spring.core.shared.Mapper;
import com.epam.spring.core.shared.NotFoundException;
import com.epam.spring.core.tickets.Ticket;
import com.epam.spring.core.tickets.TicketService;
import ma.glasnost.orika.MapperFacade;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("eventService")
public class EventServiceImpl implements EventService {

    private MapperFacade mapper = Mapper.getMapper();

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

        return mapper.map(save, Event.class);
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

        for (ShowEntity showEntity : eventEntity.getShows()) {
            int freeTickets = showRepository.countFreeTickets(showEntity.getId());
            showEntity.setFreeTicketCount(freeTickets);
        }

        return mapper.map(eventEntity, Event.class);
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

        int freeTickets = showRepository.countFreeTickets(showEntity.getId());
        showEntity.setFreeTicketCount(freeTickets);

        return mapper.map(showEntity, Show.class);
    }

    @Override
    public List<Event> getAll() {
        Iterable<EventEntity> allEntities = eventRepository.findAll();
        return mapper.mapAsList(allEntities, Event.class);
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
