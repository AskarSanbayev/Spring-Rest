package com.epam.buscompany.dao;

import com.epam.buscompany.model.entity.Driver;

import java.util.List;

public interface DriverDao extends GenericDao<Driver> {

    List<Driver> findAll();

    boolean findByNumber(int driverNumber);
}
