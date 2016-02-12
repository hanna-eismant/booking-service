package com.epam.spring.core.shared;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.EventService;
import com.epam.spring.core.tickets.Ticket;
import com.epam.spring.core.tickets.TicketService;
import com.epam.spring.core.users.User;
import com.epam.spring.core.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("bookingFacade")
public class BookingFacadeImpl implements BookingFacade {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;


    @Override
    public List<Event> getAllEventsInfo() {
        return eventService.getAll();
    }

    @Override
    public Event getEventInfo(final Long eventId) {
        return eventService.getById(eventId);
    }

    @Override
    public Map<String, Object> getUserInfo(final String name) {
        User user = userService.getByName(name);
        int ticketsCount = ticketService.getBookedTicketsCount(user);

        Map<String, Object> result = new HashMap<>(4);

        result.put("name", user.getName());
        result.put("email", user.getEmail());
        result.put("birthday", user.getBirthday());
        result.put("ticketsCount", ticketsCount);

        return result;
    }

    @Override
    public List<User> getAllUsersInfo() {
        return userService.getAll();
    }

    @Override
    public Map<String, Object> getUserTickets(final String userName) {
        Map<String, Object> result = new HashMap<>();

        User user = userService.getByName(userName);
        List<Ticket> tickets = ticketService.getBookedTickets(user);

        result.put("user", user);
        result.put("tickets", tickets);

        return result;
    }
}
