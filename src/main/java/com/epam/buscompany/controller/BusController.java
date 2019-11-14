package com.epam.buscompany.controller;

import com.epam.buscompany.model.entity.Bus;
import com.epam.buscompany.model.entity.Driver;
import com.epam.buscompany.model.exception.FreeBusException;
import com.epam.buscompany.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Validated
public class BusController {

    @Autowired
    private BusService busService;

    @GetMapping(value = "/buses")
    public List<Bus> getBusList() {
        return busService.findAll();
    }

    @DeleteMapping("/bus/{registernumber}")
    public ResponseEntity<String> deleteBus(@PathVariable("registernumber") int registerNumber) {
        busService.remove(registerNumber);

        return new ResponseEntity<>("Bus has been deleted!", HttpStatus.OK);
    }

    @GetMapping("/bus/registernumber/{registernumber}")
    public Bus findByRegisterNumber(@PathVariable("registernumber") @Min(value = 1, message = "must be more than 0") int registerNumber) {
        return busService.findByNumber(registerNumber);
    }

    @PostMapping(value = "/addbus")
    public ResponseEntity<String> postBus(@Valid @RequestBody Bus bus,
                                          @RequestParam(name = "garagenumber") String garageNumber,
                                          @RequestParam(name = "routenumber") String routeNumber) {
        int busGarageNumber = Integer.parseInt(garageNumber);
        int busRouteNumber = Integer.parseInt(routeNumber);
        busService.create(bus, busGarageNumber, busRouteNumber);
        return new ResponseEntity<>("Bus has been created!", HttpStatus.OK);
    }

    @GetMapping("/bus/busdriver/{registernumber}")
    public Driver checkForBooking(@PathVariable("registernumber") @Min(value = 1, message = "must be more than 0") int registerNumber) {
        return Optional.ofNullable(busService.findByNumber(registerNumber).getDriver()).orElseThrow(FreeBusException::new);
    }

}
