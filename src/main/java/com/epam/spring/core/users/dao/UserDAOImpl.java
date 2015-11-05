package com.epam.spring.core.users.dao;

import com.epam.spring.core.users.User;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {

    private static final String CREATE_SQL = "INSERT INTO users (id,name,email,birthday) VALUES (?,?,?,?)";
    private static final String REMOVE_SQL = "DELETE FROM users WHERE id = ?";
    private static final String FIND_BY_ID_SQL = "SELECT * FROM users WHERE id = ?";
    private static final String FIND_BY_EMAIL_SQL = "SELECT * FROM users WHERE email = ?";
    private static final String FIND_BY_NAME_SQL = "SELECT * FROM users WHERE name = ?";
    private static final String FIND_ALL_SQL = "SELECT * FROM users";
    private static final String REMOVE_ALL_SQL = "DELETE FROM users";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static int userIdCounter = 0;

    @Override
    public User create(User user) throws IllegalArgumentException {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be 'null'");
        }

        user.id = (long) ++userIdCounter;

        jdbcTemplate.update(CREATE_SQL, user.id, user.name, user.email, user.birthday.toString());
        return findById(user.id);
    }

    @Override
    public void remove(User user) throws IllegalArgumentException {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be 'null'");
        }

        if (user.id == null) {
            throw new IllegalArgumentException("User id cannot be 'null'");
        }

        jdbcTemplate.update(REMOVE_SQL, user.id);
    }

    @Override
    public User findById(Long id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("Id for search cannot be 'null'");
        }

        User user = null;
        try {
            user = jdbcTemplate.queryForObject(FIND_BY_ID_SQL, new Object[]{id}, new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            // if no user find, then return null
        }
        return user;
    }

    @Override
    public User findByEmail(String email) throws IllegalArgumentException {
        if (email == null) {
            throw new IllegalArgumentException("Email for search cannot be 'null'");
        }

        User user = null;
        try {
            user = jdbcTemplate.queryForObject(FIND_BY_EMAIL_SQL, new Object[]{email}, new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            // if no user find, then return null
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
            users = jdbcTemplate.query(FIND_BY_NAME_SQL, new Object[]{name}, new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            // if no users find, then return empty list
        }
        return users;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            users = jdbcTemplate.query(FIND_ALL_SQL, new Object[]{}, new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            // if no users find, then return empty list
        }
        return users;
    }

    @Override
    public void removeAll() {
        jdbcTemplate.update(REMOVE_ALL_SQL);
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
