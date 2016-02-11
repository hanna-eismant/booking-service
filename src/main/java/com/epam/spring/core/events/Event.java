package com.epam.spring.core.events;

import com.epam.spring.core.shared.BaseEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Event extends BaseEntity{

    private String name;
    private Double basePrice;
    private Rating rating;


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
}
