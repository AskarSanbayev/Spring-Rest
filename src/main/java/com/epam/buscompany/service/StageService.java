package com.epam.buscompany.service;

import com.epam.buscompany.model.entity.Stage;

public interface StageService extends EntityService<Stage> {

    Stage findByNumber(int stageNumber);

    void create(Stage item) ;

    void remove(int stageNumber);
}
