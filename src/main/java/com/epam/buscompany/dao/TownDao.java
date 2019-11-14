package com.epam.buscompany.dao;

import com.epam.buscompany.model.entity.Town;

import java.util.List;

public interface TownDao extends GenericDao<Town> {

    List<Town> findAll();

    Town findByName(String townName);

    void remove(String townName);
}
