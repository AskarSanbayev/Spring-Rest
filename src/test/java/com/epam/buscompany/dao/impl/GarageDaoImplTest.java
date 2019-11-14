package com.epam.buscompany.dao.impl;

import com.epam.buscompany.config.AppConfig;
import com.epam.buscompany.config.TestConfig;
import com.epam.buscompany.dao.GarageDao;
import com.epam.buscompany.model.entity.Garage;
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
public class GarageDaoImplTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    GarageDao garageDao;

    static Garage garage = null;

    @BeforeClass
    public static void setUp() throws Exception {
        garage = new Garage(99, "test", "test");
    }

    @AfterClass
    public static void tearDown() throws Exception {
        garage = null;
    }

    @Test
    @DisplayName("findAll test")
    @Order(1)
    public void findAll() {
        int size = garageDao.findAll().size();
        int userRows = JdbcTestUtils.countRowsInTable(jdbcTemplate, "company.garage");
        assertThat(garageDao.findAll(), everyItem(isA(Garage.class)));
        Assert.assertEquals(userRows, size);
    }

    @Test
    @DisplayName("findByNumber test")
    @Order(2)
    public void findByNumber() {
        Garage findGarage = garageDao.findByNumber(1);
        assertAll("properties", () -> {
                    int number = findGarage.getNumber();
                    assertThat(number, not(0));
                },
                () -> {
                    String address = findGarage.getAddress();
                    String name = findGarage.getName();
                    assertAll("name,address",
                            () -> assertThat(address, is("imanova31")),
                            () -> assertThat(name, is("first")),
                            () -> assertThat(name, isA(String.class)));
                });
        assertThat(findGarage, is(notNullValue()));
    }

    @Test
    @DisplayName("create and remove test")
    @Order(3)
    public void create() {
        garageDao.create(garage);
        Garage emptyGarage = garageDao.findByNumber(99);
        garageDao.remove(garage.getNumber());
        Assert.assertNotNull(emptyGarage);
    }

}
