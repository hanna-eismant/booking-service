package com.epam.spring.core.events.dao;

import com.epam.spring.core.events.Event;

import java.util.List;

public interface EventDAO {
    Event save(Event event);

    void remove(Event event);

    // TODO: need trow exception when there is more than one event
    Event findById(Long id);

    List<Event> findAll();
}
