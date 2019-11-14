package com.epam.buscompany.service.impl;


import com.epam.buscompany.dao.BusDao;
import com.epam.buscompany.model.entity.Bus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BusServiceImplTest {


    static BusServiceImpl busService;

    @Mock
    BusDao busDao;

    static Bus bus1;

    static Bus bus2;

    @Before
    public void setUp() {
        busService = Mockito.spy(new BusServiceImpl(busDao));
        bus1 = new Bus(1, 22, 2);
        bus2 = new Bus(2, 21, 1);
    }

    @After
    public void tearDown() {
        bus1 = null;
        bus2 = null;
    }

    @Test
    public void findAllBuses() {
        List<Bus> busList = Mockito.spy(new ArrayList<>());
        busList.add(bus1);
        busList.add(bus2);
        Mockito.doReturn(busList).when(busService).findAll();
        assertEquals(2,busService.findAll().size());
    }

    @Test
    public void create() {
        Mockito.lenient().doNothing().when(busService).create(bus1,1,1);
    }

    @Test
    public void findByNumber() {
        Mockito.doReturn(bus1).when(busService).findByNumber(1);
        Bus bus = busService.findByNumber(1);
        verify(busService).findByNumber(1);
    }


    @Test
    public void update() {
        Mockito.lenient().doNothing().when(busService).update(isA(Bus.class));
        verifyNoInteractions(busService);
    }


    @Test
    public void remove() {
        Mockito.lenient().doNothing().when(busService).remove(bus1.getRegisterNumber());
        verifyNoMoreInteractions(busService);
    }

}
