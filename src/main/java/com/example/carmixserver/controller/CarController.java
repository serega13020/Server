package com.example.carmixserver.controller;

import com.example.carmixserver.model.Automobile;
import com.example.carmixserver.model.Car;
import com.example.carmixserver.service.AutomobileService;
import com.example.carmixserver.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarController {
    private final CarService carService;
    private final AutomobileService automobileService;

    @Autowired
    public CarController(CarService carService, AutomobileService automobileService) {
        this.carService = carService;
        this.automobileService = automobileService;
    }

    @PostMapping("/car")
    public ResponseEntity<?> createCar(@RequestBody Car car) {
        carService.createCar(car);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/cars")
    public ResponseEntity<?> getCars() {
        List<Car> cars = carService.getCars();

        return cars!=null && !cars.isEmpty() ?
                new ResponseEntity<>(cars, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
