package com.epam.buscompany.controller;

import com.epam.buscompany.model.entity.Garage;
import com.epam.buscompany.service.GarageService;
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
public class GarageController {

    @Autowired
    private GarageService garageService;

    @GetMapping(value = "/garages")
    public List<Garage> getGarageList() {
        return garageService.findAll();
    }

    @DeleteMapping("/garage/{garagenumber}")
    public ResponseEntity<String> deleteGarage(@PathVariable("garagenumber") int garageNumber) {
        garageService.remove(garageNumber);

        return new ResponseEntity<>("Garage has been deleted!", HttpStatus.OK);
    }

    @GetMapping("/garage/garagenumber/{garagenumber}")
    public Garage findByGarageNumber(@PathVariable("garagenumber") int garageNumber) {
        return garageService.findByNumber(garageNumber);
    }

    @PostMapping("/addgarage")
    public Garage postGarage(@Valid @RequestBody Garage garage) {
        garageService.create(garage);
        return garage;
    }
}
