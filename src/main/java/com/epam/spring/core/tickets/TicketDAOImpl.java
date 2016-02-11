package com.epam.spring.core.tickets;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.EventInstance;
import com.epam.spring.core.events.Rating;
import com.epam.spring.core.shared.AbstractBaseDAOImpl;
import com.epam.spring.core.users.User;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.BIGINT;
import static java.sql.Types.BOOLEAN;
import static java.sql.Types.DOUBLE;
import static java.sql.Types.INTEGER;
import static java.sql.Types.VARCHAR;

@Repository
public class TicketDAOImpl extends AbstractBaseDAOImpl<Ticket> implements TicketDAO {

    private static final String COLUMN_LIST = "t.id AS t_id, t.seat, t.is_vip, t.price, t.discount_price, t.user_id, t.event_instance_id, " +
            "u.id AS u_id, u.name AS u_name, u.email, u.birthday, " +
            "i.id AS i_id, i.date, i.auditorium, i.event_id, " +
            "e.id AS e_id, e.name AS e_name, e.base_price, e.rating ";
    private static final String JOIN = "FROM tickets t LEFT JOIN users u ON t.user_id = u.id LEFT JOIN event_instances i ON t.event_instance_id = i.id LEFT JOIN events e ON i.event_id = e.id";
    private static final String CREATE_SQL = "INSERT INTO tickets (id, seat, is_vip, price, event_instance_id) VALUES (?,?,?,?,?)";
    private static final String REMOVE_SQL = "DELETE FROM tickets WHERE id = ?";
    private static final String FIND_BY_ID_SQL = "SELECT " + COLUMN_LIST + JOIN + " WHERE t.id = ?";
    private static final String FIND_BY_EVENT_AND_DATE_SQL = "SELECT " + COLUMN_LIST + JOIN + " WHERE i.event_id = ? AND i.date = ?";
    private static final String FIND_FREE_BY_EVENT_AND_DATE_SQL = FIND_BY_EVENT_AND_DATE_SQL + " AND t.user_id IS NULL";
    private static final String FIND_BY_USER_SQL = "SELECT " + COLUMN_LIST + JOIN + " WHERE t.user_id = ?";
    private static final String FIND_ALL_SQL = "SELECT " + COLUMN_LIST + JOIN;
    private static final String UPDATE_SQL = "UPDATE tickets SET discount_price=?, user_id=? WHERE id = ?";
    private long idCounter = 0;

    @Override
    public Ticket update(Ticket entity) {
        Object[] args = new Object[]{entity.getDiscountPrice(), entity.getUser().getId(), entity.getId()};
        int[] argTypes = new int[]{DOUBLE, BIGINT, BIGINT};

        jdbcTemplate.update(UPDATE_SQL, args, argTypes);
        return findById(entity.getId());
    }

    @Override
    public List<Ticket> findByEventAndDate(Event event, LocalDateTime date) {
        List<Ticket> entities = new ArrayList<>();

        try {
            entities = jdbcTemplate.query(FIND_BY_EVENT_AND_DATE_SQL, new Object[]{event.getId(), date.toString()}, createMapper());
        } catch (EmptyResultDataAccessException e) {
            // if no rows found then return empty list
        }

        return entities;
    }

    @Override
    public List<Ticket> findFreeByEventAndDate(Event event, LocalDateTime date) {
        List<Ticket> entities = new ArrayList<>();

        try {
            entities = jdbcTemplate.query(FIND_FREE_BY_EVENT_AND_DATE_SQL, new Object[]{event.getId(), date.toString()}, createMapper());
        } catch (EmptyResultDataAccessException e) {
            // if no rows found then return empty list
        }

        return entities;
    }

    @Override
    public List<Ticket> findByUser(User user) {
        List<Ticket> entities = new ArrayList<>();

        try {
            entities = jdbcTemplate.query(FIND_BY_USER_SQL, new Object[]{user.getId()}, createMapper());
        } catch (EmptyResultDataAccessException e) {
            // if no rows found then return empty list
        }

        return entities;
    }

    @Override
    protected long generateId() {
        return ++idCounter;
    }

    @Override
    protected int[] getArgTypes() {
        return new int[]{BIGINT, INTEGER, BOOLEAN, VARCHAR, DOUBLE, BIGINT};
    }

    @Override
    protected Object[] getArgs(final Ticket entity) {
        return new Object[]{entity.getId(), entity.getSeat(), entity.isVip(), entity.getBasePrice(), entity.getEventInstance().getId()};
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
    protected RowMapper<Ticket> createMapper() {
        return new TicketRowMapper();
    }

    class TicketRowMapper implements RowMapper<Ticket> {

        @Override
        public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
            Ticket ticket = new Ticket();
            ticket.setId(rs.getLong("t_id"));
            ticket.setSeat(rs.getInt("seat"));
            ticket.setVip(rs.getBoolean("is_vip"));
            ticket.setBasePrice(rs.getDouble("price") == 0.0d ? null : rs.getDouble("price"));

            // rs.getDouble("discount_price") return 0 if column contains null
            ticket.setDiscountPrice(rs.getDouble("discount_price") == 0.0d ? null : rs.getDouble("discount_price"));

            EventInstance eventInstance = new EventInstance();
            eventInstance.setId(rs.getLong("i_id"));
            eventInstance.setDate(LocalDateTime.parse(rs.getString("date")));

            Event event = new Event();
            event.setId(rs.getLong("e_id"));
            event.setName(rs.getString("e_name"));
            event.setBasePrice(rs.getDouble("base_price"));
            event.setRating(Rating.valueOf(rs.getString("rating")));

            eventInstance.setEvent(event);
            ticket.setEventInstance(eventInstance);

            long userId = rs.getLong("user_id");

            // rs.getLong("user_id") return 0 if column contains null
            // also idCounter starts from 1
            if (userId != 0) {
                User user = new User();
                user.setId(rs.getLong("u_id"));
                user.setName(rs.getString("u_name"));
                user.setEmail(rs.getString("email"));
                user.setBirthday(LocalDate.parse(rs.getString("birthday")));

                ticket.setUser(user);
            }

            return ticket;
        }
    }
}
