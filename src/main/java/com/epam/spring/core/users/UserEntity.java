package com.epam.spring.core.users;

import com.epam.spring.core.shared.RolesConverter;
import com.google.common.base.Objects;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

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
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateAsString")
    private LocalDate birthday;

    @Column(columnDefinition = "VARCHAR(255) ARRAY")
    @Convert(converter = RolesConverter.class)
    private List<UserRoles> roles = new ArrayList<>(1);

    public UserEntity() {
    }

    public UserEntity(final String _name, final String _email, final String _password, final LocalDate _birthday) {
        name = _name;
        password = _password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(final String _password) {
        password = _password;
    }

    public List<UserRoles> getRoles() {
        return roles;
    }

//    public void setRoles(final Set<UserRoles> _roles) {
//        roles = _roles;
//    }


    @Override
    public boolean equals(final Object _o) {
        if (this == _o) return true;
        if (_o == null || getClass() != _o.getClass()) return false;
        UserEntity that = (UserEntity) _o;
        return Objects.equal(getId(), that.getId()) &&
                Objects.equal(getName(), that.getName()) &&
                Objects.equal(getPassword(), that.getPassword()) &&
                Objects.equal(getEmail(), that.getEmail()) &&
                Objects.equal(getBirthday(), that.getBirthday()) &&
                Objects.equal(getRoles(), that.getRoles());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getName(), getPassword(), getEmail(), getBirthday(), getRoles());
    }
}
