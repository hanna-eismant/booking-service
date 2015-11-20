package com.epam.spring.core.tickets.dao;

import com.epam.spring.core.AbstractBaseDAOImpl;
import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.Rating;
import com.epam.spring.core.tickets.Ticket;
import com.epam.spring.core.users.User;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static java.sql.Types.*;

@Repository
public class TicketDAOImpl extends AbstractBaseDAOImpl<Ticket> implements TicketDAO {

    private static final String CREATE_SQL = "INSERT INTO tickets (id, seat, is_vip, date, price, event_id) VALUES (?,?,?,?,?,?)";
    private static final String REMOVE_SQL = "DELETE FROM tickets WHERE id = ?";

    private static final String FIND_BY_ID_SQL = "SELECT " +
            "t.id AS t_id, t.seat, t.is_vip, t.date, t.price, t.discount_price, t.event_id, t.user_id, " +
            "u.id AS u_id, u.name AS u_name, u.email, u.birthday, " +
            "e.id AS e_id, e.name AS e_name, e.base_price, e.rating " +
            "FROM tickets t LEFT JOIN users u ON t.user_id = u.id LEFT JOIN events e ON t.event_id = e.id WHERE t.id = ?";

    private static final String FIND_ALL_SQL = "SELECT " +
            "t.id AS t_id, t.seat, t.is_vip, t.date, t.price, t.discount_price, t.event_id, t.user_id, " +
            "u.id AS u_id, u.name AS u_name, u.email, u.birthday, " +
            "e.id AS e_id, e.name AS e_name, e.base_price, e.rating " +
            "FROM tickets t LEFT JOIN users u ON t.user_id = u.id LEFT JOIN events e ON t.event_id = e.id";

    private static final String UPDATE_SQL = "UPDATE tickets SET discount_price=?, user_id=? WHERE id = ?";


    @Override
    public Ticket create(Ticket entity) throws IllegalArgumentException {
        args = new Object[]{entity.id, entity.seat, entity.isVip, entity.date.toString(), entity.basePrice, entity.event.id};
        argTypes = new int[]{BIGINT, INTEGER, BOOLEAN, VARCHAR, DOUBLE, BIGINT};

        return super.create(entity);
    }

    @Override
    public Ticket update(Ticket entity) {
        args = new Object[]{entity.discountPrice, entity.user.id, entity.id};
        argTypes = new int[]{DOUBLE, BIGINT, BIGINT};

        jdbcTemplate.update(UPDATE_SQL, args, argTypes);
        return findById(entity.id);
    }

    @Override
    public List<Ticket> findByEvent(Event event) {
        return null;
    }

    @Override
    public List<User> findByUser(User user) {
        return null;
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
            ticket.id = rs.getLong("t_id");
            ticket.seat = rs.getInt("seat");
            ticket.isVip = rs.getBoolean("is_vip");
            ticket.date = LocalDateTime.parse(rs.getString("date"));
            ticket.basePrice = (rs.getDouble("price") == 0.0d ? null : rs.getDouble("price"));
            // rs.getDouble("discount_price") return 0 if column contains null
            ticket.discountPrice = (rs.getDouble("discount_price") == 0.0d ? null : rs.getDouble("discount_price"));

            Event event = new Event();
            event.id = rs.getLong("e_id");
            event.name = rs.getString("e_name");
            event.basePrice = rs.getDouble("base_price");
            event.rating = Rating.valueOf(rs.getString("rating"));

            ticket.event = event;

            long userId = rs.getLong("user_id");

            // rs.getLong("user_id") return 0 if column contains null
            // also idCounter starts from 1
            if (userId != 0) {
                User user = new User();
                user.id = rs.getLong("u_id");
                user.name = rs.getString("u_name");
                user.email = rs.getString("email");
                user.birthday = LocalDate.parse(rs.getString("birthday"));

                ticket.user = user;
            }

            return ticket;
        }
    }
}
