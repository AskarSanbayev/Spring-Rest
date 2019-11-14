package com.epam.buscompany.service;

import com.epam.buscompany.model.entity.Town;

public interface TownService extends EntityService<Town> {

    Town findByName(String name);

    void create(Town item) ;

    void remove(String townName);
}
