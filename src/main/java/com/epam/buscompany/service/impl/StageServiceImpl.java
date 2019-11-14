package com.epam.buscompany.service.impl;

import com.epam.buscompany.dao.StageDao;
import com.epam.buscompany.model.entity.Stage;
import com.epam.buscompany.model.exception.NotFoundException;
import com.epam.buscompany.service.StageService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class StageServiceImpl implements StageService {


    private StageDao stageDao;

    public StageServiceImpl(StageDao stageDao) {
        this.stageDao = stageDao;
    }

    @Transactional(readOnly = true)
    @Override
    public Stage findByNumber(int stageNumber) {
        return Optional.ofNullable(stageDao.findByNumber(stageNumber)).orElseThrow(() -> new NotFoundException("Item not found"));
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
    public void remove(int stageNumber) {
         stageDao.remove(stageNumber);

    }

    @Transactional(readOnly = true)
    @Override
    public List<Stage> findAll() {
        return stageDao.findAll();
    }
}
