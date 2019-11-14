package com.epam.buscompany.service;


import java.util.List;

public interface EntityService<T> {

    void update(T item) ;

    List<T> findAll() ;
}
