package com.epam.spring.core.events;

import com.epam.spring.core.BaseEntity;
import com.epam.spring.core.tickets.Ticket;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Scope("prototype")
public class Event extends BaseEntity{

    public String name;
    public Double basePrice;
    public Rating rating;
    private List<Ticket> tickets = new ArrayList<>();

    public Event() {
    }

    public Event(String name, Double basePrice, Rating rating) {
        this.name = name;
        this.basePrice = basePrice;
        this.rating = rating;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", basePrice=" + basePrice +
                ", rating=" + rating +
                ", tickets(" + tickets.size() + ")=" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return  Objects.equals(id, event.id) &&
                Objects.equals(name, event.name) &&
                Objects.equals(basePrice, event.basePrice) &&
                rating == event.rating;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, basePrice, rating);
    }
}
