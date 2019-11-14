package com.epam.buscompany.dao.impl;


import com.epam.buscompany.config.AppConfig;
import com.epam.buscompany.config.TestConfig;
import com.epam.buscompany.dao.BusDao;
import com.epam.buscompany.dao.DriverDao;
import com.epam.buscompany.model.entity.Bus;
import com.epam.buscompany.model.entity.Driver;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@WebAppConfiguration
public class DriverDaoImplTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    BusDao busDao;

    @Autowired
    DriverDao driverDao;

    static Bus bus = null;
    static Driver driver = null;
    static Date date = null;


    @BeforeClass
    public static void setUp() throws ParseException {
        bus = new Bus(99, 20, 1);
        date = new Date();
        driver = new Driver(99, "test", date);
        driver.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("2010-2-2"));

    }

    @AfterClass
    public static void tearDown() throws Exception {
        date = null;
        driver = null;
        bus = null;
    }

    @Test
    public void findAll() {
        int size = driverDao.findAll().size();
        int userRows = JdbcTestUtils.countRowsInTable(jdbcTemplate, "company.driver");

        assertThat(driverDao.findAll(), everyItem(isA(Driver.class)));

        assertEquals(userRows, size);
    }

    @Test
    public void findByNumber() {
        Driver find = driverDao.findByNumber(2);
        Assert.assertNotNull(find);
    }


    @Test
    public void create() {
        busDao.create(bus);
        driver.setBus(bus);
        driverDao.create(driver);

        Driver find = driverDao.findByNumber(driver.getNumber());

        driverDao.remove(driver.getNumber());
        busDao.remove(bus.getRegisterNumber());

        assertThat(driver, isA(Driver.class));
        Assert.assertNotNull(find);
    }
}
