package com.epam.spring.core.events;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import com.epam.spring.core.auditoriums.AuditoriumEntity;
import com.epam.spring.core.tickets.TicketEntity;
import com.google.common.base.Objects;

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

    @ManyToOne(targetEntity = AuditoriumEntity.class, optional = false)
    @JoinColumn(name = "auditorium_id")
    private AuditoriumEntity auditorium;

    @OneToMany(targetEntity = TicketEntity.class, mappedBy = "show", fetch = FetchType.EAGER)
    private Set<TicketEntity> tickets = new LinkedHashSet<>();

    @Transient
    private int freeTicketCount;

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

    public AuditoriumEntity getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(final AuditoriumEntity _auditorium) {
        auditorium = _auditorium;
    }

    public Set<TicketEntity> getTickets() {
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
        ShowEntity that = (ShowEntity) _o;
        return getFreeTicketCount() == that.getFreeTicketCount() && Objects.equal(getId(), that.getId()) && Objects.equal(getEvent(), that.getEvent()) &&
                Objects.equal(getDate(), that.getDate()) && Objects.equal(getAuditorium(), that.getAuditorium());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getEvent(), getDate(), getAuditorium(), getFreeTicketCount());
    }
}
