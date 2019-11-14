package com.epam.buscompany.service.impl;

import com.epam.buscompany.dao.GarageDao;
import com.epam.buscompany.model.entity.Garage;
import com.epam.buscompany.model.exception.NotFoundException;
import com.epam.buscompany.service.GarageService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class GarageServiceImpl implements GarageService {

    private GarageDao garageDao;

    public GarageServiceImpl(GarageDao garageDao) {
        this.garageDao = garageDao;
    }

    @Transactional(readOnly = true)
    @Override
    public Garage findByNumber(int garageNumber) {
        return Optional.ofNullable(garageDao.findByNumber(garageNumber)).orElseThrow(() -> new NotFoundException("Item not found"));
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
    public void remove(int garageNumber){
        garageDao.remove(garageNumber);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Garage> findAll() {
        return garageDao.findAll();
    }
}
