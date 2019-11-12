package com.epam.buscompany.dao;

import com.epam.buscompany.model.entity.Route;

import java.util.List;

public interface RouteDao extends GenericDao<Route> {
    List<Route> findAll();

    boolean findByNumber(int routeNumber);
}
