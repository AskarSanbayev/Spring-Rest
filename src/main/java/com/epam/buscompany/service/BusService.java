package com.epam.buscompany.service;

import com.epam.buscompany.model.entity.Bus;

public interface BusService extends EntityService<Bus> {

    Bus findByNumber(int registerNumber);

    void create(Bus item,int garageNumber,int routeNumber);

    void remove(int registerNumber);
}
