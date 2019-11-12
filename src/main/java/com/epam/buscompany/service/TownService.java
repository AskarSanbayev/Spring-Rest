package com.epam.buscompany.service;

import com.epam.buscompany.model.entity.Town;

public interface TownService extends EntityService<Town> {

    boolean findByName(String name);
}
