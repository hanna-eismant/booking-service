package com.epam.spring.core.users.dao;

import com.epam.spring.core.shared.AbstractBaseDAOImpl;
import com.epam.spring.core.users.User;
import org.joda.time.LocalDate;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.*;

@Repository("userDAO")
public class UserDAOImpl extends AbstractBaseDAOImpl<User> implements UserDAO {

    private static final String CREATE_SQL = "INSERT INTO users (id,name,email,birthday) VALUES (?,?,?,?)";
    private static final String REMOVE_SQL = "DELETE FROM users WHERE id = ?";
    private static final String FIND_BY_ID_SQL = "SELECT * FROM users WHERE id = ?";
    private static final String FIND_BY_EMAIL_SQL = "SELECT * FROM users WHERE email = ?";
    private static final String FIND_BY_NAME_SQL = "SELECT * FROM users WHERE name = ?";
    private static final String FIND_ALL_SQL = "SELECT * FROM users";

    @Override
    public User create(User entity) throws IllegalArgumentException {
        args = new Object[]{entity.id, entity.name, entity.email, entity.birthday.toString()};
        argTypes = new int[]{BIGINT, VARCHAR, VARCHAR, VARCHAR};

        return super.create(entity);
    }

    @Override
    public User findByEmail(String email) throws IllegalArgumentException {
        if (email == null) {
            throw new IllegalArgumentException("Email for search cannot be 'null'");
        }

        User user = null;
        try {
            user = jdbcTemplate.queryForObject(FIND_BY_EMAIL_SQL, new Object[]{email}, createMapper());
        } catch (EmptyResultDataAccessException e) {
            // if no user found, then return null
        }
        return user;
    }

    @Override
    public List<User> findByName(String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("Name for search cannot be 'null'");
        }

        List<User> users = new ArrayList<>();
        try {
            users = jdbcTemplate.query(FIND_BY_NAME_SQL, new Object[]{name}, createMapper());
        } catch (EmptyResultDataAccessException e) {
            // if no users found, then return empty list
        }
        return users;
    }

    @Override
    protected String getCreateSql() {
        return CREATE_SQL;
    }

    @Override
    protected String getRemoveSql() {
        return REMOVE_SQL;
    }

    @Override
    protected String getFindByIdSql() {
        return FIND_BY_ID_SQL;
    }

    @Override
    protected String getFindAllSql() {
        return FIND_ALL_SQL;
    }

    @Override
    protected RowMapper<User> createMapper() {
        return new UserRowMapper();
    }

    class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.id = rs.getLong("id");
            user.name = rs.getString("name");
            user.email = rs.getString("email");
            user.birthday = LocalDate.parse(rs.getString("birthday"));
            return user;
        }
    }
}
