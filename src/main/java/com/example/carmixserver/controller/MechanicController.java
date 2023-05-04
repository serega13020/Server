package com.example.carmixserver.controller;

import com.example.carmixserver.model.Mechanic;
import com.example.carmixserver.service.MechanicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MechanicController {
    private final MechanicService mechanicService;

    public MechanicController(MechanicService mechanicService) {
        this.mechanicService = mechanicService;
    }

    @PostMapping("/mechanic")
    public ResponseEntity<?> createMech(@RequestBody Mechanic mechanic) {
        mechanicService.create(mechanic);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/mechanic/{id}")
    public ResponseEntity<?> getMechanicById(@PathVariable(name = "id")int id) {
        Mechanic mechanic = mechanicService.getMechanic(id);
        return mechanic!=null ?
                new ResponseEntity<>(mechanic, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/mechanics")
    public ResponseEntity<?> getMechanics() {
        List<Mechanic> mechanicList = mechanicService.getMechanics();

        return mechanicList!=null && !mechanicList.isEmpty() ?
                new ResponseEntity<>(mechanicList, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
