package com.epam.spring.core.events;

import com.epam.spring.core.auditoriums.Auditorium;
import com.epam.spring.core.shared.BaseEntity;
import com.epam.spring.core.tickets.Ticket;
import org.joda.time.LocalDateTime;

import java.util.List;

public class EventInstance extends BaseEntity {

    private Event event;
    private LocalDateTime date;
    private Auditorium auditorium;
    private List<Ticket> tickets;

    public Event getEvent() {
        return event;
    }

    public void setEvent(final Event _event) {
        event = _event;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(final LocalDateTime _date) {
        date = _date;
    }

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(final Auditorium _auditorium) {
        auditorium = _auditorium;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
}
