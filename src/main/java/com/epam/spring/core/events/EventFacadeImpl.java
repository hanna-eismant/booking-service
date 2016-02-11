package com.epam.spring.core.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventFacadeImpl implements EventFacade {

    @Autowired
    private EventService eventService;

    @Override
    public List<Event> getAllEventsInfo() {
        return eventService.getAll();
    }
}
