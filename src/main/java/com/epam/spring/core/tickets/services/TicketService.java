package com.epam.spring.core.tickets.services;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.users.User;
import org.joda.time.LocalDateTime;

public interface TicketService {

    Double getTicketPrice(Event event, LocalDateTime date, Integer seat, boolean isVip, User user);
}
