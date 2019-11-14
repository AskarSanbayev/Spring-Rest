package com.epam.buscompany.service.impl;

import com.epam.buscompany.dao.BusDao;
import com.epam.buscompany.dao.DriverDao;
import com.epam.buscompany.dao.GarageDao;
import com.epam.buscompany.dao.RouteDao;
import com.epam.buscompany.model.entity.Bus;
import com.epam.buscompany.model.entity.Garage;
import com.epam.buscompany.model.entity.Route;
import com.epam.buscompany.model.exception.NotFoundException;
import com.epam.buscompany.service.BusService;
import com.epam.buscompany.service.InfoMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class BusServiceImpl implements BusService {

    private BusDao busDao;

    @Autowired
    private InfoMailSender infoMailSender;

    @Autowired
    private DriverDao driverDao;

    @Autowired
    private RouteDao routeDao;

    @Autowired
    private GarageDao garageDao;

    public BusServiceImpl(BusDao busDao) {
        this.busDao = busDao;
    }

    @Transactional(readOnly = true)
    @Override
    public Bus findByNumber(int registerNumber) {
        return Optional.ofNullable(busDao.findByNumber(registerNumber)).orElseThrow(() -> new NotFoundException("Item not found"));
    }

    @Override
    public void create(Bus item, int garageNumber, int routeNumber) {
        Route route = routeDao.findByNumber(routeNumber);
        Garage garage = garageDao.findByNumber(garageNumber);
        item.setRoute(route);
        item.setGarage(garage);
        infoMailSender.sendMail("Bus adding", "New bus added" +
                "Register number:" + item.getRegisterNumber() + "\n" +
                "Size :" + item.getSize() + "\n" +
                "Garage number:" + garageNumber + "\n" +
                "Route number:" + routeNumber + " !");
        busDao.create(item);
    }

    @Override
    public void update(Bus item) {
        busDao.update(item);
    }

    @Override
    public void remove(int registerNumber) {
        Bus bus = findByNumber(registerNumber);
        bus.getDriver().setBus(null);
        driverDao.update(bus.getDriver());
        busDao.remove(registerNumber);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Bus> findAll() {
        return busDao.findAll();
    }
}
