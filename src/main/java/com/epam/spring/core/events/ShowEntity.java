package com.epam.spring.core.events;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "shows")
public class ShowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = EventEntity.class)
    @JoinColumn(name = "event_id")
    private EventEntity event;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTimeAsString")
    private LocalDateTime date;

    @Column(nullable = false)
    private String auditorium;

//    private List<Ticket> tickets = new ArrayList<>();
//
//    private int freeTicketCount;

    public ShowEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long _id) {
        id = _id;
    }

    public EventEntity getEvent() {
        return event;
    }

    public void setEvent(final EventEntity _event) {
        event = _event;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(final LocalDateTime _date) {
        date = _date;
    }

    public String getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(final String _auditorium) {
        auditorium = _auditorium;
    }

//    public List<Ticket> getTickets() {
//        return tickets;
//    }
//
//    public int getFreeTicketCount() {
//        return freeTicketCount;
//    }
//
//    public void setFreeTicketCount(final int _freeTicketCount) {
//        freeTicketCount = _freeTicketCount;
//    }
}
