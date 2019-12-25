package com.epam.buscompany.dao.impl;

import com.epam.buscompany.config.TestConfig;
import com.epam.buscompany.dao.RouteDao;
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

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WebAppConfiguration
public class RouteDaoImplTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    RouteDao routeDao;

    static Route route = null;

    @BeforeClass
    public static void setUp() {
        route = new Route(100, 22);
    }

    @AfterClass
    public static void tearDown() {
        route = null;
    }

    @Test
    @DisplayName("findAll test")
    @Order(1)
    public void findAll() {
        int size = routeDao.findAll().size();
        int userRows = JdbcTestUtils.countRowsInTable(jdbcTemplate, "company.route");
        assertThat(routeDao.findAll(), everyItem(isA(Route.class)));
        Assert.assertEquals(userRows, size);
    }

    @Test
    @DisplayName("findByNumber test")
    @Order(2)
    public void findByNumber() {
        Route findRoute = routeDao.findByNumber(1);
        assertAll("properties", () -> {
                    int number = findRoute.getNumber();
                    assertThat(number, not(0));
                },
                () -> {
                    int passengerAverage = findRoute.getPassengerAverage();
                    assertAll("passenger avg",
                            () -> assertThat(passengerAverage, is(20)),
                            () -> assertThat(passengerAverage, isA(Integer.class)));
                });
        assertThat(findRoute, is(notNullValue()));
    }

    @Test
    @DisplayName("create and remove test")
    @Order(3)
    public void create() {
        routeDao.create(route);
        Route emptyRoute = routeDao.findByNumber(100);
        routeDao.remove(route.getNumber());
        Assert.assertNotNull(emptyRoute);
    }
}
