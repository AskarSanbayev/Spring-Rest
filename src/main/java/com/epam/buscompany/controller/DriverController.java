package com.epam.buscompany.controller;


import com.epam.buscompany.model.entity.Driver;
import com.epam.buscompany.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping(value = "/drivers")
    public List<Driver> getDriverList() {
        return driverService.findAll();
    }

    @DeleteMapping("/driver/{drivernumber}")
    public ResponseEntity<String> deleteDriver(@PathVariable("drivernumber") int driverNumber) {
        driverService.remove(driverNumber);

        return new ResponseEntity<>("Driver has been deleted!", HttpStatus.OK);
    }

    @GetMapping("/driver/{drivernumber}")
    public Driver findByDriverNumber(@PathVariable("drivernumber") int driverNumber) {
        return driverService.findByNumber(driverNumber);
    }

    @PostMapping("/adddriver")
    public Driver postDriver(@Valid @RequestBody Driver driver,
                             @RequestParam(name = "busnumber") String busNumber) {
        int driverBusNumber = Integer.parseInt(busNumber);
        driverService.create(driver, driverBusNumber);
        return driver;
    }
}
