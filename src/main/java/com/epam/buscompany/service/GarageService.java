package com.epam.buscompany.service;

import com.epam.buscompany.model.entity.Garage;

public interface GarageService extends EntityService<Garage> {

    Garage findByNumber(int garageNumber);

    void create(Garage item);

    void remove(int garageNumber);
}
