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

    private static final String CREATE_SQL = "INSERT INTO statistic (id,type,counter) VALUES (?,?,?)";
    private static final String REMOVE_SQL = "DELETE FROM statistic WHERE id = ?";
    private static final String FIND_BY_ID_SQL = "SELECT * FROM statistic WHERE id = ?";
    private static final String FIND_BY_TYPE_SQL = "SELECT * FROM statistic WHERE type = ?";
    private static final String FIND_ALL_SQL = "SELECT * FROM statistic";


    @Override
    public Statistic create(Statistic entity) throws IllegalArgumentException {
        args = new Object[]{entity.id, entity.type, entity.counter};
        argTypes = new int[]{BIGINT, VARCHAR, BIGINT};

        return super.create(entity);
    }

    @Override
    public Statistic findByType(String type) throws IllegalArgumentException {
        if (type == null) {
            throw new IllegalArgumentException("Email for search cannot be 'null'");
        }

        Statistic statistic = new Statistic();
        try {
            statistic = jdbcTemplate.queryForObject(FIND_BY_TYPE_SQL, new Object[]{type}, createMapper());
        } catch (EmptyResultDataAccessException e) {
            // if no statistic found, then create for type and counter = 0;
            statistic.type = type;
            statistic.counter = 0L;
            statistic = create(statistic);
        }

        return statistic;
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
            statistic.id = resultSet.getLong("id");
            statistic.type = resultSet.getString("type");
            statistic.counter = resultSet.getLong("counter");

            return statistic;
        }
    }

}
