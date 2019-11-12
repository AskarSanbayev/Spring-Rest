package com.epam.buscompany.service;


import java.util.List;

public interface EntityService<T> {
    void create(T item) ;

    void update(T item) ;

    boolean remove(T item);

    List<T> findAll() ;
}
