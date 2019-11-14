package com.epam.buscompany.controller;

import com.epam.buscompany.config.TestConfig;
import com.epam.buscompany.config.WebAppConfig;
import com.epam.buscompany.model.entity.Bus;
import com.epam.buscompany.model.exception.NotFoundException;
import com.epam.buscompany.service.BusService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class, WebAppConfig.class})
@WebAppConfiguration
public class BusControllerTest {

    private MockMvc mockMvc;

    private static final int UNKNOWN_FIND_NUMBER = Integer.MAX_VALUE;
    private static final int TEST_REGISTER_NUMBER = 1;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private BusService busService;

    static Bus bus;

    @Before
    public void init() {
        bus = new Bus(1, 25, 0);
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @After
    public void tearDown() {
        bus = null;
        mockMvc = null;
    }

    @Test
    public void getAllBuses() throws Exception {
        mockMvc.perform(get("/api/buses"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(busService.findAll().size())))
                .andExpect(jsonPath("$[0].registernumber", is(1)))
                .andExpect(jsonPath("$[0].size", is(25)))
                .andExpect(jsonPath("$[0].deck", is(1)));
    }

    @Test(expected = NotFoundException.class)
    public void findByRegisterNumber_shouldThrowNotFoundException() throws Exception {
        when(busService.findByNumber(UNKNOWN_FIND_NUMBER)).thenThrow(new NotFoundException());

        mockMvc.perform(get("/api/registernumber/{number}", UNKNOWN_FIND_NUMBER))
                .andExpect(status().isNotFound());

    }

    @Test
    public void findByRegisterNumber() throws Exception {
        mockMvc.perform(get("/api/bus/registernumber/{number}", TEST_REGISTER_NUMBER))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.registernumber", is(1)))
                .andExpect(jsonPath("$.size", is(25)))
                .andExpect(jsonPath("$.deck", is(1)));
    }

    @Test
    public void postBus_shouldGetInvalidParameterError() throws Exception {
        mockMvc.perform(post("/api/addbus")
                .content(objToJson(bus))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.timestamp", is(notNullValue())))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors", hasSize(1)))
                .andExpect(jsonPath("$.errors", hasItem("number must be 1 or 2")));
    }

    @Test
    public void getBookedBus() throws Exception {
        mockMvc.perform(get("/api/bus/busdriver/{number}", TEST_REGISTER_NUMBER))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.number", is(1)))
                .andExpect(jsonPath("$.name", is("askar")))
                .andExpect(jsonPath("$.bus.registernumber", is(1)));
    }

    private String objToJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
