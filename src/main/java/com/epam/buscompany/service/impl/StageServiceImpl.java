package com.epam.buscompany.service.impl;

import com.epam.buscompany.dao.StageDao;
import com.epam.buscompany.model.entity.Stage;
import com.epam.buscompany.service.StageService;

import java.util.List;

public class StageServiceImpl implements StageService {


    private StageDao stageDao;

    public StageServiceImpl(StageDao stageDao) {
        this.stageDao = stageDao;
    }

    @Override
    public boolean findByName(int stageNumber) {
        boolean find = stageDao.findByNumber(stageNumber);
        return find;
    }

    @Override
    public void create(Stage item) {
        stageDao.create(item);
    }

    @Override
    public void update(Stage item) {
        stageDao.update(item);
    }

    @Override
    public boolean remove(Stage item) {
        boolean find = stageDao.remove(item);
        return find;
    }

    @Override
    public List<Stage> findAll() {
        List<Stage> stageList = stageDao.findAll();
        return stageList;
    }
}
