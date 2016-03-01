package com.epam.spring.core.auditoriums;

import com.epam.spring.core.shared.converters.ListToArrayConverter;
import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "auditoriums")
public class AuditoriumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer seats;

    @Column(name = "vip_seats", columnDefinition = "INTEGER ARRAY")
    @Convert(converter = ListToArrayConverter.class)
    private List<Integer> vipSeats = new ArrayList<>();

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

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(final Integer _seats) {
        seats = _seats;
    }

    public List<Integer> getVipSeats() {
        return vipSeats;
    }

    public void setVipSeats(final List<Integer> _vipSeats) {
        vipSeats = _vipSeats;
    }

    @Override
    public boolean equals(final Object _o) {
        if (this == _o)
            return true;
        if (_o == null || getClass() != _o.getClass())
            return false;
        AuditoriumEntity that = (AuditoriumEntity) _o;
        return Objects.equal(getId(), that.getId()) && Objects.equal(getName(), that.getName()) && Objects.equal(getSeats(), that.getSeats());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getName(), getSeats());
    }
}
