package com.epam.buscompany.dao;

import com.epam.buscompany.model.entity.Bus;

import java.util.List;

public interface BusDao extends GenericDao<Bus> {

    List<Bus> findAll();

    boolean findByNumber(int registerNumber);

}
