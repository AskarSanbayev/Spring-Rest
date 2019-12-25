package com.epam.buscompany.dao.impl;

import com.epam.buscompany.config.TestConfig;
import com.epam.buscompany.dao.BusDao;
import com.epam.buscompany.dao.DriverDao;
import com.epam.buscompany.dao.GarageDao;
import com.epam.buscompany.dao.RouteDao;
import com.epam.buscompany.model.entity.Bus;
import com.epam.buscompany.model.entity.Driver;
import com.epam.buscompany.model.entity.Garage;
import com.epam.buscompany.model.entity.Route;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WebAppConfiguration
public class BusDaoImplTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    BusDao busDao;

    @Autowired
    RouteDao routeDao;

    @Autowired
    DriverDao driverDao;

    @Autowired
    GarageDao garageDao;


    static Bus bus = null;
    static Route route = null;
    static Garage garage = null;
    static Driver driver = null;
    static Date date = null;

    @BeforeClass
    public static void setUp() throws Exception {
        bus = new Bus(99, 20, 1);
        route = new Route(98, 22);
        date = new Date();
        garage = new Garage(99, "test", "test");
        driver = new Driver(99, "test", date);
        driver.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("2010-2-2"));
    }

    @AfterClass
    public static void tearDown() {
        bus = null;
        route = null;
        garage = null;
        date = null;
        driver = null;
    }

    @Test
    @DisplayName("findAll test")
    @Order(1)
    public void findAll() {
        int size = busDao.findAll().size();
        System.out.println(busDao.findAll());
        int userRows = JdbcTestUtils.countRowsInTable(jdbcTemplate, "company.bus");
        assertThat(busDao.findAll(), everyItem(isA(Bus.class)));
        Assert.assertEquals(userRows, size);
    }

    @Test
    @DisplayName("findByNumber test")
    @Order(2)
    public void findByNumber() {
        Bus find = busDao.findByNumber(10);
        assertAll("properties", () -> {
                    int id = route.getNumber();
                    assertThat(id, not(0));
                },
                () -> {
                    int size = bus.getSize();
                    int deck = bus.getDeck();
                    assertAll("size and deck",
                            () -> assertThat(size, is(20)),
                            () -> assertThat(deck, isA(Integer.class)));
                });
        assertThat(find, is(notNullValue()));
    }

    @Test
    @DisplayName("create and remove test")
    @Order(3)
    public void create() {
        Set<Bus> buses = new HashSet<>(Collections.singletonList(bus));
        route.setBuses(buses);
        garage.setBuses(buses);
        driver.setBus(bus);

        routeDao.create(route);
        garageDao.create(garage);
        busDao.create(bus);
        driverDao.create(driver);
        Bus find = busDao.findByNumber(bus.getRegisterNumber());

        driverDao.remove(driver.getNumber());
        busDao.remove(bus.getRegisterNumber());
        garageDao.remove(garage.getNumber());
        routeDao.remove(route.getNumber());

        assertThat(bus, isA(Bus.class));
        Assert.assertNotNull(find);
    }
}