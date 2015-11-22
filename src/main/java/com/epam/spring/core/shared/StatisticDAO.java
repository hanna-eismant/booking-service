package com.epam.spring.core.shared;

public interface StatisticDAO extends BaseDAO<Statistic> {
    Statistic findByType(String type) throws IllegalArgumentException;
}
