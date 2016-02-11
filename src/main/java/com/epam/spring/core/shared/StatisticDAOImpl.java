package com.epam.spring.core.shared;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.Types.BIGINT;
import static java.sql.Types.VARCHAR;

@Repository
public class StatisticDAOImpl extends AbstractBaseDAOImpl<Statistic> implements StatisticDAO {

    private long idCounter = 0;

    private static final String CREATE_SQL = "INSERT INTO statistic (id,name,type,counter) VALUES (?,?,?,?)";
    private static final String REMOVE_SQL = "DELETE FROM statistic WHERE id = ?";
    private static final String FIND_BY_ID_SQL = "SELECT * FROM statistic WHERE id = ?";
    private static final String FIND_BY_NAME_AND_TYPE_SQL = "SELECT * FROM statistic WHERE name = ? AND type = ?";
    private static final String FIND_ALL_SQL = "SELECT * FROM statistic";
    private static final String UPDATE_SQL = "UPDATE statistic SET counter = ? WHERE id = ?";

    @Override
    public Statistic findByNameAndType(String name, String type) throws IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name for search cannot be 'null' or empty");
        }

        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Type for search cannot be 'null' or empty");
        }

        Statistic statistic = new Statistic();
        try {
            statistic = jdbcTemplate.queryForObject(FIND_BY_NAME_AND_TYPE_SQL, new Object[]{name, type}, createMapper());
        } catch (EmptyResultDataAccessException e) {
            // if no statistic found, then create for name/type and counter = 0;
            statistic.setName(name);
            statistic.setType(type);
            statistic.setCounter(0L);
            statistic = create(statistic);
        }

        return statistic;
    }

    @Override
    public Statistic incrementCounter(String name, String type) throws IllegalArgumentException {
        Statistic statistic = findByNameAndType(name, type);
        statistic.setCounter(statistic.getCounter() + 1);
        Object[] args = new Object[]{statistic.getCounter(), statistic.getId()};
        int[] argTypes = new int[]{BIGINT, BIGINT};

        jdbcTemplate.update(UPDATE_SQL, args, argTypes);
        return findById(statistic.getId());
    }

    @Override
    protected long generateId() {
        return ++idCounter;
    }

    @Override
    protected int[] getArgTypes() {
        return new int[]{BIGINT, VARCHAR, VARCHAR, BIGINT};
    }

    @Override
    protected Object[] getArgsForCreate(final Statistic entity) {
        return new Object[]{entity.getId(), entity.getName(), entity.getType(), entity.getCounter()};
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
    protected RowMapper<Statistic> createMapper() {
        return new StatisticRowMapper();
    }


    class StatisticRowMapper implements RowMapper<Statistic> {

        @Override
        public Statistic mapRow(ResultSet resultSet, int i) throws SQLException {
            Statistic statistic = new Statistic();
            statistic.setId(resultSet.getLong("id"));
            statistic.setName(resultSet.getString("name"));
            statistic.setType(resultSet.getString("type"));
            statistic.setCounter(resultSet.getLong("counter"));

            return statistic;
        }
    }

}
