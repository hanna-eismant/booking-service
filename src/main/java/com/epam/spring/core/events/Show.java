package com.epam.spring.core.events;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDateTime;

import com.epam.spring.core.auditoriums.Auditorium;
import com.epam.spring.core.shared.BaseEntity;
import com.epam.spring.core.tickets.Ticket;
import com.google.common.base.Objects;

public class Show extends BaseEntity {

    private Event event;
    private LocalDateTime date;
    private Auditorium auditorium;
    private List<Ticket> tickets = new ArrayList<>();
    private int freeTicketCount;

    public Show() {
    }

    public Show(final Event _event, final LocalDateTime _date, final int _freeTicketCount) {
        event = _event;
        date = _date;
        freeTicketCount = _freeTicketCount;
    }

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

    public int getFreeTicketCount() {
        return freeTicketCount;
    }

    public void setFreeTicketCount(final int _freeTicketCount) {
        freeTicketCount = _freeTicketCount;
    }

    @Override
    public boolean equals(final Object _o) {
        if (this == _o)
            return true;
        if (_o == null || getClass() != _o.getClass())
            return false;
        Show show = (Show) _o;
        return getFreeTicketCount() == show.getFreeTicketCount() && Objects.equal(getId(), show.getId()) && Objects.equal(getEvent(), show.getEvent()) &&
                Objects.equal(getDate(), show.getDate()) && Objects.equal(getAuditorium(), show.getAuditorium());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getEvent(), getDate(), getAuditorium(), getFreeTicketCount());
    }
}
