package com.epam.spring.core.users;

import com.google.common.base.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_account")
public class UserAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private Double money;

    public Long getId() {
        return id;
    }

    public void setId(final Long _id) {
        id = _id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(final UserEntity _user) {
        user = _user;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(final Double _money) {
        money = _money;
    }

    @Override
    public boolean equals(final Object _o) {
        if (this == _o) return true;
        if (_o == null || getClass() != _o.getClass()) return false;
        UserAccountEntity that = (UserAccountEntity) _o;
        return Objects.equal(getId(), that.getId()) &&
                Objects.equal(getUser(), that.getUser()) &&
                Objects.equal(getMoney(), that.getMoney());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getUser(), getMoney());
    }
}
