package com.epam.spring.core.users;

import com.epam.spring.core.shared.AbstractBaseDAOImpl;
import org.joda.time.LocalDate;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.Types.BIGINT;
import static java.sql.Types.VARCHAR;

@Repository("userDAO")
public class UserDAOImpl extends AbstractBaseDAOImpl<User> implements UserDAO {

    private long idCounter = 0;

    private static final String CREATE_SQL = "INSERT INTO users (id,name,email,birthday) VALUES (?,?,?,?)";
    private static final String REMOVE_SQL = "DELETE FROM users WHERE id = ?";
    private static final String FIND_BY_ID_SQL = "SELECT * FROM users WHERE id = ?";
    private static final String FIND_BY_EMAIL_SQL = "SELECT * FROM users WHERE email = ?";
    private static final String FIND_BY_NAME_SQL = "SELECT * FROM users WHERE name = ?";
    private static final String FIND_ALL_SQL = "SELECT * FROM users";

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
    public User findByName(String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("Name for search cannot be 'null'");
        }

        User user = null;
        try {
            user = jdbcTemplate.queryForObject(FIND_BY_NAME_SQL, new Object[]{name}, createMapper());
        } catch (EmptyResultDataAccessException e) {
            // if no users found, then return empty list
        }
        return user;
    }

    @Override
    protected long generateId() {
        return ++idCounter;
    }

    @Override
    protected int[] getArgTypes() {
        return new int[]{BIGINT, VARCHAR, VARCHAR, VARCHAR};
    }

    @Override
    protected Object[] getArgsForCreate(final User entity) {
        return new Object[]{entity.getId(), entity.getName(), entity.getEmail(), entity.getBirthday().toString()};
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
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setBirthday(LocalDate.parse(rs.getString("birthday")));
            return user;
        }
    }
}
