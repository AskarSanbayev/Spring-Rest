package com.epam.buscompany.controller;


import com.epam.buscompany.model.entity.Town;
import com.epam.buscompany.service.TownService;
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
public class TownController {

    @Autowired
    private TownService townService;

    @GetMapping(value = "/towns")
    public List<Town> getTownList() {
        return townService.findAll();
    }


    @DeleteMapping("/town/{townname}")
    public ResponseEntity<String> deleteTown(@PathVariable("townname") String townName) {
        townService.remove(townName);

        return new ResponseEntity<>("Town has been deleted!", HttpStatus.OK);
    }

    @GetMapping("/town/{townname}")
    public Town findByTownName(@PathVariable("townname") String townName) {
        return townService.findByName(townName);
    }

    @PostMapping("/addtown")
    public Town postTown(@Valid @RequestBody Town town) {
        townService.create(town);
        return town;
    }
}
