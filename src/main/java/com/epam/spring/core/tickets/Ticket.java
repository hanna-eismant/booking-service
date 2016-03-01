package com.epam.spring.core.tickets;

import com.epam.spring.core.events.Show;
import com.epam.spring.core.shared.BaseEntity;
import com.epam.spring.core.users.User;
import com.google.common.base.Objects;

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

    @Override
    public boolean equals(final Object _o) {
        if (this == _o)
            return true;
        if (_o == null || getClass() != _o.getClass())
            return false;
        Ticket ticket = (Ticket) _o;
        return isVip() == ticket.isVip() && Objects.equal(getId(), ticket.getId()) && Objects.equal(getUser(), ticket.getUser()) &&
                Objects.equal(getSeat(), ticket.getSeat()) && Objects.equal(getBasePrice(), ticket.getBasePrice()) &&
                Objects.equal(getDiscountPrice(), ticket.getDiscountPrice()) && Objects.equal(getShow(), ticket.getShow());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getUser(), getSeat(), isVip(), getBasePrice(), getDiscountPrice(), getShow());
    }
}
