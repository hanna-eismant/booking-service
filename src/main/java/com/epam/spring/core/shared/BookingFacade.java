package com.epam.spring.core.shared;

import com.epam.spring.core.api.soap.SoapUser;
import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.Show;
import com.epam.spring.core.shared.exceptions.DuplicateException;
import com.epam.spring.core.shared.exceptions.NotEnoughMoneyException;
import com.epam.spring.core.shared.exceptions.NotFoundException;
import com.epam.spring.core.shared.exceptions.TicketAlreadyBookedException;
import com.epam.spring.core.tickets.Ticket;
import com.epam.spring.core.users.User;
import org.joda.time.LocalDate;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface BookingFacade {

    Map<String, Object> getUserTickets(final String userName) throws NotFoundException;

    Map<String, Object> getUserInfo(final String name) throws NotFoundException;

    User getUser(final String name) throws NotFoundException;

    List<User> getAllUsersInfo();

    List<Event> getAllEventsInfo();

    Event getEventInfo(final Long eventId) throws NotFoundException;

    Map<String, List<User>> parseUsers(InputStream inputStream) throws IOException;

    Map<String, List<Event>> parseEvents(InputStream inputStream) throws IOException;

    Map<String, Object> getEventTickets(Long showId) throws NotFoundException;

    Show getShow(Long showId) throws NotFoundException;

    Ticket bookTicket(String userName, Long ticketId) throws NotFoundException, TicketAlreadyBookedException, NotEnoughMoneyException;

    SoapUser getSoapUser(final String userName) throws NotFoundException;

    List<SoapUser> getAllSoapUsersInfo();

    void registerUser(String name, String email, String password, LocalDate birthday) throws DuplicateException;
}
