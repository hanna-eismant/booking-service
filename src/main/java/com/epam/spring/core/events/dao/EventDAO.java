package com.epam.spring.core.events.dao;

import com.epam.spring.core.events.Event;

import java.util.List;

public interface EventDAO {
    Event create(Event event);

    void remove(Event event);

    Event findById(Long id);

    List<Event> findAll();

    void removeAll();
}
