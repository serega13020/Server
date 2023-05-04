package com.example.carmixserver.controller;

import com.example.carmixserver.model.Automobile;
import com.example.carmixserver.model.Job;
import com.example.carmixserver.service.AutomobileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AutomobileController {
    private final AutomobileService automobileService;

    public AutomobileController(AutomobileService automobileService) {
        this.automobileService = automobileService;
    }

    @PostMapping("/automobile")
    public ResponseEntity<?> createAuto(@RequestBody Automobile automobile) {

        automobileService.createAutomobile(automobile);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/automobile/{id}")
    public ResponseEntity<?> getAutoById(@PathVariable(name="id") int id){
        Automobile automobile = automobileService.getAutomobile(id);
        return automobile!=null ?
                new ResponseEntity<>(automobile, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/automobiles")
    public ResponseEntity<?> getAutomobiles(){
        List<Automobile> automobiles = automobileService.getAutomobiles();
        return automobiles!=null && !automobiles.isEmpty() ?
                new ResponseEntity<>(automobiles, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
