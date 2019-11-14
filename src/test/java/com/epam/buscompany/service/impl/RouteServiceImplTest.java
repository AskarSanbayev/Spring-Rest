package com.epam.buscompany.service.impl;


import com.epam.buscompany.model.entity.Route;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RouteServiceImplTest {

    @Mock
    static RouteServiceImpl routeService;

    static Route route;

    static Route route2;

    @Before
    public void setUp() {
        route = new Route(2, 20);
        route2 = new Route(3, 21);
    }

    @After
    public void tearDown() {
        route = null;
        route2 = null;
    }

    @Test
    public void findAllRoutes() {
        List<Route> routeList = Arrays.asList(route, route2);
        when(routeService.findAll()).thenReturn(routeList);
        assertThat(routeService.findAll(), is(routeList));
        verify(routeService, never()).remove(any(Integer.class));
        verify(routeService, times(1)).findAll();
    }

    @Test
    public void findByNumber() {
        when(routeService.findByNumber(2)).thenReturn(route);
        routeService.findByNumber(2);
        verify(routeService, times(1)).findByNumber(2);
        verifyNoMoreInteractions(routeService);
    }

    @Test
    public void create() {
        Route route = new Route(2, 20);
        ArgumentCaptor<Route> valueCapture = ArgumentCaptor.forClass(Route.class);
        doNothing().when(routeService).create(valueCapture.capture());
        routeService.create(route);
        verify(routeService, times(1)).create(route);

        assertEquals(2, valueCapture.getValue().getNumber());
    }


    @Test
    public void update() {
        Mockito.lenient().doNothing().when(routeService).update(isA(Route.class));
        verifyNoInteractions(routeService);
    }


    @Test
    public void remove() {
        Mockito.lenient().doNothing().when(routeService).remove(route.getNumber());
        doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);

            assertEquals(2, arg0);
            return null;
        }).when(routeService).remove(anyInt());
        routeService.remove(route.getNumber());
        verify(routeService, times(1)).remove(route.getNumber());
    }
}
