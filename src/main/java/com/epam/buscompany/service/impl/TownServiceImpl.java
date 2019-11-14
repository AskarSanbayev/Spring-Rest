package com.epam.buscompany.service.impl;

import com.epam.buscompany.dao.TownDao;
import com.epam.buscompany.model.entity.Town;
import com.epam.buscompany.model.exception.NotFoundException;
import com.epam.buscompany.service.TownService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class TownServiceImpl implements TownService {

    private TownDao townDao;

    public TownServiceImpl(TownDao townDao) {
        this.townDao = townDao;
    }

    @Transactional(readOnly = true)
    @Override
    public Town findByName(String townName) {
        return Optional.ofNullable(townDao.findByName(townName)).orElseThrow(() -> new NotFoundException("Item not found"));
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
    public void remove(String townName) {
        townDao.remove(townName);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Town> findAll() {
        return townDao.findAll();
    }
}
