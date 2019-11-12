package com.epam.buscompany.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MainController {

    @RequestMapping(value = "/")
    public List getIndex() {
        return Arrays.asList("test", "test", "test");
    }
}
