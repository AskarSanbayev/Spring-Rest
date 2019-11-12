package com.epam.buscompany.service.impl;

import com.epam.buscompany.dao.GarageDao;
import com.epam.buscompany.model.entity.Garage;
import com.epam.buscompany.service.GarageService;

import java.util.List;

public class GarageServiceImpl implements GarageService {

    private GarageDao garageDao;

    public GarageServiceImpl(GarageDao garageDao) {
        this.garageDao = garageDao;
    }

    @Override
    public boolean findByNumber(int garageNumber) {
        boolean find = garageDao.findByNumber(garageNumber);
        return find;
    }

    @Override
    public void create(Garage item) {
        garageDao.create(item);
    }

    @Override
    public void update(Garage item)  {
        garageDao.update(item);
    }

    @Override
    public boolean remove(Garage item){
        boolean find = garageDao.remove(item);
        return find;
    }

    @Override
    public List<Garage> findAll() {
        List<Garage> garageList = garageDao.findAll();
        return garageList;
    }
}
