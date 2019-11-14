package com.epam.buscompany.service.impl;

import com.epam.buscompany.dao.BusDao;
import com.epam.buscompany.dao.RouteDao;
import com.epam.buscompany.model.entity.Bus;
import com.epam.buscompany.model.entity.Route;
import com.epam.buscompany.model.exception.NotFoundException;
import com.epam.buscompany.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Transactional
public class RouteServiceImpl implements RouteService {

    private RouteDao routeDao;

    public RouteServiceImpl(RouteDao routeDao) {
        this.routeDao = routeDao;
    }

    @Transactional(readOnly = true)
    @Override
    public Route findByNumber(int routeNumber) {
        return Optional.ofNullable(routeDao.findByNumber(routeNumber)).orElseThrow(() -> new NotFoundException("Item not found"));
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
    public void remove(int routeNumber) {
        routeDao.remove(routeNumber);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Route> findAll() {
        return routeDao.findAll();
    }
}
