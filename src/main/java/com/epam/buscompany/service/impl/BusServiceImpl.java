package com.epam.buscompany.service.impl;

import com.epam.buscompany.dao.BusDao;
import com.epam.buscompany.model.entity.Bus;
import com.epam.buscompany.service.BusService;

import java.util.List;

public class BusServiceImpl implements BusService {

    private BusDao busDao;

    public BusServiceImpl(BusDao busDao) {
        this.busDao = busDao;
    }

    @Override
    public boolean findByNumber(int registerNumber) {
        boolean find = busDao.findByNumber(registerNumber);
        return find;
    }


    @Override
    public void create(Bus item) {
        busDao.create(item);
    }

    @Override
    public void update(Bus item) {
        busDao.update(item);
    }

    @Override
    public boolean remove(Bus item) {
        boolean find = busDao.remove(item);
        return find;
    }

    @Override
    public List<Bus> findAll() {
        List<Bus> busList = busDao.findAll();
        return busList;
    }
}
