package com.epam.spring.core.events;

import com.epam.spring.core.shared.BaseEntity;
import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.List;

public class Event extends BaseEntity {

    private String name;
    private Double basePrice;
    private Rating rating;
    private List<Show> shows = new ArrayList<>();

    public Event() {
    }

    public Event(final String _name, final Double _basePrice, final Rating _rating) {
        name = _name;
        basePrice = _basePrice;
        rating = _rating;
    }

    public String getName() {
        return name;
    }

    public void setName(final String _name) {
        name = _name;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(final Double _basePrice) {
        basePrice = _basePrice;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(final Rating _rating) {
        rating = _rating;
    }

    public List<Show> getShows() {
        return shows;
    }

    @Override
    public boolean equals(final Object _o) {
        if (this == _o)
            return true;
        if (_o == null || getClass() != _o.getClass())
            return false;
        Event event = (Event) _o;
        return Objects.equal(getId(), event.getId()) && Objects.equal(getName(), event.getName()) && Objects.equal(getBasePrice(), event.getBasePrice()) &&
                getRating() == event.getRating();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getName(), getBasePrice(), getRating());
    }
}
