package com.epam.spring.core.events.dao;

import com.epam.spring.core.Util;
import com.epam.spring.core.events.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventDAOMapImpl implements EventDAO {

    private static Map<Long, Event> events = new HashMap<>();

    @Override
    public Event create(Event event) {
        Long id;
        do {
            id = Util.generateId();
        } while (events.containsKey(id));

        event.id = id;
        events.put(id, event);
        return event;
    }

    @Override
    public void remove(Event event) {
        if (events.containsKey(event.id)) {
            events.remove(event.id);
        }
    }

    @Override
    public Event findById(Long id) {
        if (events.containsKey(id)){
            return events.get(id);
        } else {
            return null;
        }
    }

    @Override
    public List<Event> findAll() {
        return new ArrayList<>(events.values());
    }

}
