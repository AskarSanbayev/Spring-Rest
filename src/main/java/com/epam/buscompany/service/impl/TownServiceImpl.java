package com.epam.buscompany.service.impl;

import com.epam.buscompany.dao.TownDao;
import com.epam.buscompany.model.entity.Town;
import com.epam.buscompany.service.TownService;

import java.util.List;

public class TownServiceImpl implements TownService {

    private TownDao townDao;

    public TownServiceImpl(TownDao townDao) {
        this.townDao = townDao;
    }

    @Override
    public boolean findByName(String townName) {
        boolean find = townDao.findByName(townName);
        return find;
    }

    @Override
    public void create(Town item) {
        townDao.create(item);
    }

    @Override
    public void update(Town item) {
        townDao.update(item);
    }

    @Override
    public boolean remove(Town item) {
        boolean find = townDao.remove(item);
        return find;
    }

    @Override
    public List<Town> findAll() {
        List<Town> townList = townDao.findAll();
        return townList;
    }
}
