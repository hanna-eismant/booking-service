package com.epam.spring.core.users;

import com.google.common.base.Objects;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateAsString")
    private LocalDate birthday;

    public UserEntity() {
    }

    public UserEntity(final String _name, final String _email, final LocalDate _birthday) {
        name = _name;
        email = _email;
        birthday = _birthday;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(final String _email) {
        email = _email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(final LocalDate _birthday) {
        birthday = _birthday;
    }

    @Override
    public boolean equals(final Object _o) {
        if (this == _o) return true;
        if (_o == null || getClass() != _o.getClass()) return false;
        UserEntity that = (UserEntity) _o;
        return Objects.equal(getId(), that.getId()) &&
                Objects.equal(getName(), that.getName()) &&
                Objects.equal(getEmail(), that.getEmail()) &&
                Objects.equal(getBirthday(), that.getBirthday());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getName(), getEmail(), getBirthday());
    }
}
