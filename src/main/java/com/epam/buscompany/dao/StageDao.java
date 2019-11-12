package com.epam.buscompany.dao;

import com.epam.buscompany.model.entity.Stage;

import java.util.List;

public interface StageDao extends GenericDao<Stage> {

    List<Stage> findAll();

    boolean findByNumber(int stageNumber);
}
