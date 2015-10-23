package com.epam.spring.core.events.dao;

import com.epam.spring.core.Util;
import com.epam.spring.core.events.Event;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EventDAOImpl implements EventDAO {

    private static Map<Long, Event> events = new HashMap<Long, Event>();

    public Event create(Event event) {
        Long id;
        do {
            id = Util.generateId();
        } while (events.containsKey(id));

        event.id = id;
        events.put(id, event);
        return event;
    }

    public void remove(Event event) {
        if (events.containsKey(event.id)) {
            events.remove(event.id);
        }
    }

    public Event findById(Long id) {
        if (events.containsKey(id)){
            return events.get(id);
        } else {
            return null;
        }
    }

    public List<Event> findAll() {
        return new ArrayList<Event>(events.values());
    }
}
