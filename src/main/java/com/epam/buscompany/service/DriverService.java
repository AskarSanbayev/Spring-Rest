package com.epam.buscompany.service;

import com.epam.buscompany.model.entity.Driver;

public interface DriverService extends EntityService<Driver>{


    Driver findByNumber(int driverNumber);

    void remove(int driverNumber);

    void create(Driver item,int busNumber) ;
}
