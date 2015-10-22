package com.epam.spring.core.events.services;

import com.epam.spring.core.auditoriums.Auditorium;
import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.Rating;
import com.epam.spring.core.events.dao.EventDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDAO eventDAO;

    public void setEventDAO(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    public Event create(String name, List<Date> airDates, List<Date> airTimes, Integer basePrice, Rating rating) {
        Event event = new Event(name, airDates, airTimes, basePrice, rating);
        event = eventDAO.save(event);
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
        // TODO: create tickets ???


    }
}
