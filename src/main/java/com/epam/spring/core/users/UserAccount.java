package com.epam.spring.core.users;

import com.google.common.base.Objects;

public class UserAccount {

    private Long id;
    private Double money;

    public UserAccount() {
    }

    public UserAccount(final Long _id, final Double _money) {
        id = _id;
        money = _money;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long _id) {
        id = _id;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(final Double _money) {
        money = _money;
    }

    @Override
    public boolean equals(final Object _o) {
        if (this == _o)
            return true;
        if (_o == null || getClass() != _o.getClass())
            return false;
        UserAccount that = (UserAccount) _o;
        return Objects.equal(getId(), that.getId()) && Objects.equal(getMoney(), that.getMoney());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getMoney());
    }
}
