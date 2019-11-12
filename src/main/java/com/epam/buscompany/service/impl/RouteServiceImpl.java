package com.epam.buscompany.service.impl;

import com.epam.buscompany.dao.RouteDao;
import com.epam.buscompany.model.entity.Route;
import com.epam.buscompany.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {

    private RouteDao routeDao;

    public RouteServiceImpl(RouteDao routeDao) {
        this.routeDao = routeDao;
    }

    @Override
    public boolean findByNumber(int routeNumber) {
        boolean find = routeDao.findByNumber(routeNumber);
        return find;
    }

    @Override
    public void create(Route item) {
        routeDao.create(item);
    }

    @Override
    public void update(Route item) {
        routeDao.update(item);
    }

    @Override
    public boolean remove(Route item) {
        boolean find = routeDao.remove(item);
        return find;
    }

    @Override
    public List<Route> findAll() {
        List<Route> routeList = routeDao.findAll();
        return routeList;
    }
}
