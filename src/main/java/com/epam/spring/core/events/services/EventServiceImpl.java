package com.epam.spring.core.events.services;

import com.epam.spring.core.auditoriums.Auditorium;
import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.Rating;
import com.epam.spring.core.events.dao.EventDAO;
import com.epam.spring.core.tickets.Ticket;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EventServiceImpl implements EventService, ApplicationContextAware {

    @Autowired
    private EventDAO eventDAO;

    private ApplicationContext context;

    public void setEventDAO(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    public Event create(String name, List<Date> airDates, List<Date> airTimes, Integer basePrice, Rating rating) {
        Event event = context.getBean(Event.class);
        event.name = name;
        event = eventDAO.create(event);
        return event;
    }

    public void remove(Event event) {
        eventDAO.remove(event);
    }

    public Event getById(Long id) {
        return eventDAO.findById(id);
    }

    public List<Event> getAll() {
        return eventDAO.findAll();
    }

    public void assignAuditorium(Event event, Auditorium auditorium, Date date) {
        // TODO: check date and event date

        int airTimesCount = event.getAirTimes().size();
        for (int i = 0; i < airTimesCount; i++) {
            for (int seat = 0; seat < auditorium.seats; seat++) {
                Ticket ticket = new Ticket(seat, event.basePrice);
                event.getTickets().add(ticket);
            }
        }
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
