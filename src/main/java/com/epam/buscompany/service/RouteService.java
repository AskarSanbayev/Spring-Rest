package com.epam.buscompany.service;

import com.epam.buscompany.model.entity.Route;

public interface RouteService  extends EntityService<Route>{

    boolean findByNumber(int routeNumber);
}
