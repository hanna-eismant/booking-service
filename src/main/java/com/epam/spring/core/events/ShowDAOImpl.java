package com.epam.spring.core.events;

import com.epam.spring.core.auditoriums.AuditoriumService;
import com.epam.spring.core.shared.AbstractBaseDAOImpl;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.BIGINT;
import static java.sql.Types.DOUBLE;
import static java.sql.Types.VARCHAR;

@Repository("showDAO")
public class ShowDAOImpl extends AbstractBaseDAOImpl<Show> implements ShowDAO {

    private long idCounter = 0;

    private static final String COLUMN_LIST = "s.id AS s_id, s.date, s.auditorium, s.event_id, COUNT(t.id) AS free_tickets";
    private static final String JOIN = " FROM shows s LEFT JOIN tickets t ON t.show_id = s.id AND t.user_id IS NULL";
    private static final String END = " GROUP BY s.id ORDER BY s.date DESC";

    private static final String CREATE_SQL = "INSERT INTO shows (id,date,auditorium,event_id) VALUES (?,?,?,?)";
    private static final String REMOVE_SQL = "DELETE FROM shows WHERE id = ?";

    private static final String FIND_BY_ID_SQL = "SELECT " + COLUMN_LIST + JOIN + " WHERE s.id = ?" + END;
    private static final String FIND_ALL_SQL = "SELECT " + COLUMN_LIST + JOIN + END;

    private static final String FIND_BY_EVENT_SQL = "SELECT " + COLUMN_LIST + JOIN + " WHERE s.event_id = ?" + END;

    @Autowired
    private AuditoriumService auditoriumService;


    @Override
    public List<Show> getByEvent(final Long eventId) {
        if (eventId == null) {
            throw new IllegalArgumentException("Event id for search cannot be 'null'");
        }

        List<Show> list = new ArrayList<>();
        try {
            list = jdbcTemplate.query(FIND_BY_EVENT_SQL, new Object[]{eventId}, createMapper());
        } catch (EmptyResultDataAccessException e) {
            // if no entities found, then return empty list
        }

        return list;
    }

    @Override
    protected long generateId() {
        return ++idCounter;
    }

    @Override
    protected int[] getArgTypes() {
        return new int[]{BIGINT, VARCHAR, DOUBLE, VARCHAR};
    }

    @Override
    protected Object[] getArgsForCreate(final Show entity) {
        return new Object[]{entity.getId(), entity.getDate(), entity.getAuditorium().getName(), entity.getEvent().getId()};
    }

    @Override
    protected String getFindByIdSql() {
        return FIND_BY_ID_SQL;
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
    protected String getFindAllSql() {
        return FIND_ALL_SQL;
    }

    @Override
    protected RowMapper<Show> createMapper() {
        return (rs, rowNum) -> {
            Show show = new Show();
            show.setId(rs.getLong("s_id"));
            show.setDate(LocalDateTime.parse(rs.getString("date")));
            show.setAuditorium(auditoriumService.getAuditorium(rs.getString("auditorium")));
            show.setFreeTicketCount(rs.getInt("free_tickets"));
            return show;
        };
    }
}
