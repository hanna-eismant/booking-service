package com.epam.spring.core.events;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.common.base.Objects;

@Entity
@Table(name = "events")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "base_price", nullable = false)
    private Double basePrice;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Rating rating;

    @OneToMany(targetEntity = ShowEntity.class, mappedBy = "event", fetch = FetchType.EAGER)
    private Set<ShowEntity> shows = new LinkedHashSet<>();

    public EventEntity() {
    }

    public EventEntity(final String _name, final Double _basePrice, final Rating _rating) {
        name = _name;
        basePrice = _basePrice;
        rating = _rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long _id) {
        id = _id;
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

    public Set<ShowEntity> getShows() {
        return shows;
    }

    @Override
    public boolean equals(final Object _o) {
        if (this == _o)
            return true;
        if (_o == null || getClass() != _o.getClass())
            return false;
        EventEntity that = (EventEntity) _o;
        return Objects.equal(getId(), that.getId()) && Objects.equal(getName(), that.getName()) && Objects.equal(getBasePrice(), that.getBasePrice()) &&
                getRating() == that.getRating();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getName(), getBasePrice(), getRating());
    }
}
