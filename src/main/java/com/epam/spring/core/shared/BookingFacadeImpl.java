package com.epam.spring.core.shared;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.EventService;
import com.epam.spring.core.events.Show;
import com.epam.spring.core.tickets.Ticket;
import com.epam.spring.core.tickets.TicketService;
import com.epam.spring.core.users.User;
import com.epam.spring.core.users.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("bookingFacade")
public class BookingFacadeImpl implements BookingFacade {

    private Gson gson;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    @PostConstruct
    private void init() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(LocalDate.class, new LocalDateGSONAdapter());
        builder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeGSONAdapter());
        gson = builder.create();
    }

    @Override
    public List<Event> getAllEventsInfo() {
        return eventService.getAll();
    }

    @Override
    public Event getEventInfo(final Long eventId) throws NotFoundException {
        Event event = eventService.getById(eventId);

        if (event == null) {
            throw new NotFoundException("Event doesn't exist");
        }

        return event;
    }

    @Override
    public Map<String, Object> getUserInfo(final String name) throws NotFoundException {
        User user = userService.getByName(name);

        if (user == null) {
            throw new NotFoundException("User doesn't exist");
        }

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
    public Map<String, Object> getUserTickets(final String userName) throws NotFoundException {
        Map<String, Object> result = new HashMap<>();

        User user = userService.getByName(userName);

        if (user == null) {
            throw new NotFoundException("User doesn't exist");
        }

        List<Ticket> tickets = ticketService.getBookedTickets(userName);

        result.put("user", user);
        result.put("tickets", tickets);

        return result;
    }

    @Override
    public Map<String, Object> getEventTickets(final Long showId) throws NotFoundException {
        Map<String, Object> result = new HashMap<>();
        Show show = eventService.getShowById(showId);
        List<Ticket> tickets = ticketService.getForShow(showId);
        result.put("tickets", tickets);
        result.put("show", show);
        return result;
    }

    @Override
    public Map<String, List<User>> parseUsers(final InputStream inputStream) throws IOException {
        Reader reader = new InputStreamReader(inputStream);
        JsonReader jsonReader = new JsonReader(reader);

        jsonReader.beginArray();
        List<User> error = new ArrayList<>();
        List<User> success = new ArrayList<>();

        while (jsonReader.hasNext()) {
            User user = gson.fromJson(jsonReader, User.class);
            try {
                user = userService.register(user.getName(), user.getEmail(), user.getBirthday());
                success.add(user);
            } catch (Exception e) {
                e.printStackTrace();
                error.add(user);
            }
        }

        Map<String, List<User>> result = new HashMap<>(2);
        result.put("error", error);
        result.put("success", success);

        return result;
    }

    @Override
    public Map<String, List<Event>> parseEvents(final InputStream inputStream) throws IOException {
        Reader reader = new InputStreamReader(inputStream);
        JsonReader jsonReader = new JsonReader(reader);

        jsonReader.beginArray();
        List<Event> error = new ArrayList<>();
        List<Event> success = new ArrayList<>();

        while (jsonReader.hasNext()) {
            Event event = gson.fromJson(jsonReader, Event.class);
            try {
                event = eventService.create(event.getName(), event.getBasePrice(), event.getRating());
                success.add(event);
            } catch (Exception e) {
                e.printStackTrace();
                error.add(event);
            }
        }

        Map<String, List<Event>> result = new HashMap<>(2);
        result.put("error", error);
        result.put("success", success);

        return result;
    }
}

