package com.example.carmixserver.controller;

import com.example.carmixserver.model.Car;
import com.example.carmixserver.model.Customer;
import com.example.carmixserver.service.AutomobileService;
import com.example.carmixserver.service.CarService;
import com.example.carmixserver.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CustomerController {
    private final CustomerService customerService;
    private final CarService carService;
    private final AutomobileService automobileService;

    @Autowired
    public CustomerController(CustomerService customerService, CarService carService, AutomobileService automobileService) {
        this.customerService = customerService;
        this.carService = carService;
        this.automobileService = automobileService;
    }

    @PostMapping("/customer")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        System.err.println(customer);
        if (customer.getCars()!=null && !customer.getCars().isEmpty()) {
            List<Car> cars = customer.getCars()
                    .stream()
                    .map(c->carService.getCarByVin(c.getVin()))
                    .collect(Collectors.toList());
            if (cars.get(0)==null) {
                cars = new ArrayList<>();
                System.err.println("Car haven't been searched");
                for (Car car : customer.getCars()) {
                    carService.createCar(car);
                    cars.add(car);
                }
            }
            customer.setCars(cars);
        }
        customerService.create(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/customers")
    public ResponseEntity<?> getCustomers() {
        List<Customer> customerList = customerService.getCustomers();

        return customerList!=null && !customerList.isEmpty() ?
                new ResponseEntity<>(customerList, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
