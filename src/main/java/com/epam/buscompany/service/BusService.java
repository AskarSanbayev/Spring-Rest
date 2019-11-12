package com.epam.buscompany.service;

import com.epam.buscompany.model.entity.Bus;

public interface BusService extends EntityService<Bus> {

    boolean findByNumber(int registerNumber);
}
