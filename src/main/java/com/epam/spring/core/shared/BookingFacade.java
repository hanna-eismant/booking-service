package com.epam.spring.core.shared;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.users.User;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface BookingFacade {

    Map<String, Object> getUserTickets(final String userName);

    Map<String, Object> getUserInfo(final String name);

    List<User> getAllUsersInfo();

    List<Event> getAllEventsInfo();

    Event getEventInfo(final Long eventId);

    Map<String, List<User>> parseUsers(InputStream inputStream) throws Exception;
}
