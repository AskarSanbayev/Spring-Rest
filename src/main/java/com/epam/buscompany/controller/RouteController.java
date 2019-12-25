package com.epam.buscompany.controller;

import com.epam.buscompany.model.entity.Route;
import com.epam.buscompany.service.RouteService;
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
public class RouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping(value = "/routes")
    public List<Route> getBusList() {
        return routeService.findAll();
    }

    @DeleteMapping("/route/{routenumber}")
    public ResponseEntity<String> deleteRoute(@PathVariable("routenumber") int routeNumber) {
        routeService.remove(routeNumber);

        return new ResponseEntity<>("Route has been deleted!", HttpStatus.OK);
    }

    @GetMapping("/route/{routenumber}")
    public Route findByRouteNumber(@PathVariable("routenumber") int routeNumber) {
        return routeService.findByNumber(routeNumber);
    }

    @PostMapping("/addroute")
    public Route postRoute(@Valid @RequestBody Route route) {
        routeService.create(route);
        return route;
    }
}
