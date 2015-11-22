package com.epam.spring.core.shared;

public interface StatisticDAO extends BaseDAO<Statistic> {
    Statistic findByNameAndType(String name, String type) throws IllegalArgumentException;
    Statistic incrementCounter(String name, String type) throws IllegalArgumentException;
}
