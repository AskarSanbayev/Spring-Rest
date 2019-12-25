package com.epam.buscompany.controller;

import com.epam.buscompany.model.entity.Stage;
import com.epam.buscompany.service.StageService;
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
public class StageController {

    @Autowired
    private StageService stageService;

    @GetMapping(value = "/stages")
    public List<Stage> getStageList() {
        return stageService.findAll();
    }

    @DeleteMapping("/stage/{stagenumber}")
    public ResponseEntity<String> deleteStage(@PathVariable("stagenumber") int stageNumber) {
        stageService.remove(stageNumber);

        return new ResponseEntity<>("Stage has been deleted!", HttpStatus.OK);
    }

    @GetMapping("/stage/{stagenumber}")
    public Stage findByStageNumber(@PathVariable("stagenumber") int stageNumber) {
        return stageService.findByNumber(stageNumber);
    }

    @PostMapping("/addstage")
    public Stage postStage(@Valid @RequestBody Stage stage) {
        stageService.create(stage);
        return stage;
    }
}
