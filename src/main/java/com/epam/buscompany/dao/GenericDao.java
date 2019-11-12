package com.epam.buscompany.dao;

public interface GenericDao<T> {
    void create(T item) ;

    void update(T item);

    boolean remove(T item);
}
