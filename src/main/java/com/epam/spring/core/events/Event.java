package com.epam.spring.core.events;

import com.epam.spring.core.shared.BaseEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Scope("prototype")
public class Event extends BaseEntity{

    public String name;
    public Double basePrice;
    public Rating rating;

    public Event() {
    }

    public Event(String name, Double basePrice, Rating rating) {
        this.name = name;
        this.basePrice = basePrice;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public Rating getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", basePrice=" + basePrice +
                ", rating=" + rating +
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
