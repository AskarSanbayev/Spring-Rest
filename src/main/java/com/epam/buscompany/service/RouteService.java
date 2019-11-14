package com.epam.buscompany.service;

import com.epam.buscompany.model.entity.Route;

public interface RouteService  extends EntityService<Route>{

    Route findByNumber(int routeNumber);

    void create(Route item) ;

    void remove(int routeNumber);
}
