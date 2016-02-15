package com.epam.spring.core.shared;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.users.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface BookingFacade {

    Map<String, Object> getUserTickets(final String userName) throws NotFoundException;

    Map<String, Object> getUserInfo(final String name) throws NotFoundException;

    List<User> getAllUsersInfo();

    List<Event> getAllEventsInfo();

    Event getEventInfo(final Long eventId) throws NotFoundException;

    Map<String, List<User>> parseUsers(InputStream inputStream) throws IOException;

    Map<String, List<Event>> parseEvents(InputStream inputStream) throws IOException;
}
