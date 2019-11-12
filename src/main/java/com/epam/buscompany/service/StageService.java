package com.epam.buscompany.service;

import com.epam.buscompany.model.entity.Stage;

public interface StageService extends EntityService<Stage> {

    boolean findByName(int stageNumber);
}
