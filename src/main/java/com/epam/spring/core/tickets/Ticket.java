package com.epam.spring.core.tickets;

import com.epam.spring.core.events.Show;
import com.epam.spring.core.shared.BaseEntity;
import com.epam.spring.core.users.User;

public class Ticket extends BaseEntity {

    private User user;
    private Integer seat;
    private boolean isVip;
    private Double basePrice;
    private Double discountPrice;
    private Show show;

    public User getUser() {
        return user;
    }

    public void setUser(final User _user) {
        user = _user;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(final Integer _seat) {
        seat = _seat;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(final boolean _vip) {
        isVip = _vip;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(final Double _basePrice) {
        basePrice = _basePrice;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(final Double _discountPrice) {
        discountPrice = _discountPrice;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(final Show _show) {
        show = _show;
    }
}
