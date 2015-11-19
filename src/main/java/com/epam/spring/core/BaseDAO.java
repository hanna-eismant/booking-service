package com.epam.spring.core;

import java.util.List;

public interface BaseDAO<T> {

    T create(T t) throws IllegalArgumentException;

    void remove(T t) throws IllegalArgumentException;

    T findById(Long id) throws IllegalArgumentException;

    List<T> findAll();

}
