package com.epam.buscompany.service.impl;

import com.epam.buscompany.dao.DriverDao;
import com.epam.buscompany.model.entity.Driver;
import com.epam.buscompany.service.DriverService;

import java.util.List;

public class DriverServiceImpl implements DriverService {

    private DriverDao driverDao;

    public DriverServiceImpl(DriverDao driverDao) {
        this.driverDao = driverDao;
    }

    @Override
    public boolean findByNumber(int driverNumber) {
        boolean find = driverDao.findByNumber(driverNumber);
        return find;
    }

    @Override
    public void create(Driver item) {
        driverDao.create(item);
    }

    @Override
    public void update(Driver item) {
        driverDao.update(item);
    }

    @Override
    public boolean remove(Driver item) {
        boolean find = driverDao.remove(item);
        return find;
    }

    @Override
    public List<Driver> findAll() {
        List<Driver> driverList = driverDao.findAll();
        return driverList;
    }
}
