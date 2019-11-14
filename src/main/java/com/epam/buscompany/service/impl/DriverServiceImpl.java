package com.epam.buscompany.service.impl;

import com.epam.buscompany.dao.BusDao;
import com.epam.buscompany.dao.DriverDao;
import com.epam.buscompany.model.entity.Bus;
import com.epam.buscompany.model.entity.Driver;
import com.epam.buscompany.model.exception.NotFoundException;
import com.epam.buscompany.service.DriverService;
import com.epam.buscompany.service.InfoMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class DriverServiceImpl implements DriverService {

    private DriverDao driverDao;

    @Autowired
    private BusDao busDao;

    @Autowired
    private InfoMailSender infoMailSender;

    public DriverServiceImpl(DriverDao driverDao) {
        this.driverDao = driverDao;
    }

    @Transactional(readOnly = true)
    @Override
    public Driver findByNumber(int driverNumber) {
        return Optional.ofNullable(driverDao.findByNumber(driverNumber)).orElseThrow(() -> new NotFoundException("Item not found"));
    }

    @Override
    public void create(Driver item,int busNumber) {
        Bus bus = busDao.findByNumber(busNumber);
        item.setBus(bus);
        infoMailSender.sendMail("New Driver!","New driver added" +
                "Driver number:" + item.getNumber() + "\n" +
                "Name :" + item.getName() + "\n" +
                "Bus number:" + item.getBus().getRegisterNumber() + "\n" +
                "Birthday:" + item.getBirthday() + " !");
        driverDao.create(item);
    }

    @Override
    public void update(Driver item) {
        driverDao.update(item);
    }

    @Override
    public void remove(int driverNumber) {
        driverDao.remove(driverNumber);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Driver> findAll() {
        return driverDao.findAll();
    }
}
