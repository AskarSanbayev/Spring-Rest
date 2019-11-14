package com.epam.buscompany.controller;


import com.epam.buscompany.config.TestConfig;
import com.epam.buscompany.config.WebAppConfig;
import com.epam.buscompany.model.entity.Route;
import com.epam.buscompany.service.RouteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, WebAppConfig.class})
@WebAppConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RouteControllerTest {

    private MockMvc mockMvc;

    private static final int UNKNOWN_FIND_NUMBER = Integer.MAX_VALUE;
    private static final int TEST_ROUTE_NUMBER = 1;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private RouteService routeService;

    static Route testRoute;

    @Before
    public void init() {
        testRoute = new Route(100, 22);
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @After
    public void tearDown() {
        testRoute = null;
        mockMvc = null;
    }

    @Test
    @Order(1)
    public void getAllRoutes() throws Exception {
        mockMvc.perform(get("/api/routes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(routeService.findAll().size())))
                .andExpect(jsonPath("$[0].number", is(1)))
                .andExpect(jsonPath("$[0].passengerAverage", is(20)));
    }

    @Test
    @Order(2)
    public void findByRegisterNumber_shouldNotFound() throws Exception {
        mockMvc.perform(get("/api/routenumber/{number}", UNKNOWN_FIND_NUMBER))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(3)
    public void findByRouteNumber() throws Exception {
        mockMvc.perform(get("/api/route/routenumber/{number}", TEST_ROUTE_NUMBER))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.number", is(1)))
                .andExpect(jsonPath("$.passengerAverage", is(20)));
    }

    @Test
    @Order(5)
    public void deleteRoute() throws Exception {
        mockMvc.perform(delete("/api/route/100"))
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    public void postRoute() throws Exception {
        mockMvc.perform(post("/api/addroute")
                .content(objToJson(testRoute))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", is(100)))
                .andExpect(jsonPath("$.passengerAverage", is(22)));
    }

    private String objToJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
