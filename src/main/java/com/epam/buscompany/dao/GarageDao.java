package com.epam.buscompany.dao;

import com.epam.buscompany.model.entity.Garage;

import java.util.List;

public interface GarageDao extends GenericDao<Garage> {
    List<Garage> findAll();

    Garage findByNumber(int garageNumber);

    void remove(int garageNumber);
}
