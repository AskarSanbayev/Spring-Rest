package com.epam.buscompany.service;

import com.epam.buscompany.model.entity.Garage;

public interface GarageService extends EntityService<Garage> {

    boolean findByNumber(int garageNumber);
}
