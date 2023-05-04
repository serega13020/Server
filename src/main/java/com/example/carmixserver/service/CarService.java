package com.example.carmixserver.service;

import com.example.carmixserver.model.Automobile;
import com.example.carmixserver.model.Car;
import com.example.carmixserver.repository.AutomobileRepository;
import com.example.carmixserver.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;
    private final AutomobileRepository automobileRepository;

    @Autowired
    public CarService(CarRepository carRepository, AutomobileRepository automobileRepository) {
        this.carRepository = carRepository;
        this.automobileRepository = automobileRepository;
    }

    public Car createCar(Car car) {
        if (car.getAutomobile()!=null) {
            Automobile auto = automobileRepository.getAutomobileByAll(car.getAutomobile().getBrend(), car.getAutomobile().getModel(), car.getAutomobile().getSeries());
            if (auto==null) {
                auto = automobileRepository.save(car.getAutomobile());
            }
            System.err.println("Creation");
            car.setAutomobile(auto);
        } else {
            return null;
        }
        return carRepository.save(car);
    }

    public Car getCar(int id) {
        return carRepository.findById(id).get();
    }

    public List<Car> getCars() {
        return carRepository.findAll();
    }

    public Car getCarByVin(String vin) {
        return carRepository.getCarByVin(vin);
    }
}
