package com.epam.buscompany.service;

import com.epam.buscompany.model.entity.Driver;

public interface DriverService extends EntityService<Driver>{


    boolean findByNumber(int driverNumber);
}
