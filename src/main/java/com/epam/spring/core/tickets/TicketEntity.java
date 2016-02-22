package com.epam.spring.core.tickets;

import com.epam.spring.core.events.ShowEntity;
import com.epam.spring.core.users.UserEntity;
import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tickets")
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer seat;

    @Column(name = "is_vip")
    private boolean isVip;

    @Column(name = "price", nullable = false)
    private Double basePrice;

    @Column(name = "discount_price")
    private Double discountPrice;

    @ManyToOne(targetEntity = ShowEntity.class, optional = false)
    @JoinColumn(name = "show_id")
    private ShowEntity show;

    @ManyToOne(targetEntity = UserEntity.class, optional = true)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public Long getId() {
        return id;
    }

    public void setId(final Long _id) {
        id = _id;
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

    public ShowEntity getShow() {
        return show;
    }

    public void setShow(final ShowEntity _show) {
        show = _show;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(final UserEntity _user) {
        user = _user;
    }

    @Override
    public boolean equals(final Object _o) {
        if (this == _o) return true;
        if (_o == null || getClass() != _o.getClass()) return false;
        TicketEntity that = (TicketEntity) _o;
        return isVip() == that.isVip() &&
                Objects.equal(getId(), that.getId()) &&
                Objects.equal(getSeat(), that.getSeat()) &&
                Objects.equal(getBasePrice(), that.getBasePrice()) &&
                Objects.equal(getDiscountPrice(), that.getDiscountPrice()) &&
                Objects.equal(getShow(), that.getShow()) &&
                Objects.equal(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getSeat(), isVip(), getBasePrice(), getDiscountPrice(), getShow(), getUser());
    }
}
