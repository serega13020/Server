package com.example.carmixserver.controller;

import com.example.carmixserver.model.*;
import com.example.carmixserver.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class OrderController {
    private final OrderService orderService;
    private final JobService jobService;
    private final MechanicService mechanicService;
    private final CustomerService customerService;
    private final CarService carService;
    private final AutomobileService automobileService;

    public OrderController(OrderService orderService, JobService jobService, MechanicService mechanicService, CustomerService customerService, CarService carService, AutomobileService automobileService) {
        this.orderService = orderService;
        this.jobService = jobService;
        this.mechanicService = mechanicService;
        this.customerService = customerService;
        this.carService = carService;
        this.automobileService = automobileService;
    }

    @PostMapping("/order")
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        System.err.println(order);
        Car car = carService.getCarByVin(order.getCar().getVin());

        if (car==null) {
            order.setCar(carService.createCar(order.getCar()));
            order.getCar().getAutomobile().setCar(List.of(order.getCar()));
        }
        else {
            order.setCar(car);
        }

        Customer customer = customerService.getCustomerByNameAndSurnameAndPhoneNumber(order.getCustomer().getFirstName(), order.getCustomer().getLastName(), order.getCustomer().getPhoneNumber());

        if (customer==null) {
            order.setCustomer(customerService.create(order.getCustomer()));
            order.getCustomer().setCars(List.of(order.getCar()));
            System.err.println("Created customer " + order.getCustomer());
        } else
            order.setCustomer(customer);


        List<Job> jobs = order.getJobs()
                .stream()
                .map(j-> jobService.getJobByTitleAndPrice(j.getTitle(), j.getPrice()))
                .collect(Collectors.toList());

        if (!jobs.isEmpty() && jobs.get(0)==null) {
            for (Job job : order.getJobs()) {
                List<Automobile> automobiles = job
                        .getAutomobiles()
                        .stream()
                        .map(automobileService::getAutomobileByAll)
                        .collect(Collectors.toList());
                if (automobiles != null && automobiles.get(0) == null) {
                    System.err.println("Inner object is " + job.getAutomobiles().get(0).getBrend());
                    job.getAutomobiles().forEach(automobileService::createAutomobile);
                    automobiles = job.getAutomobiles();
                }
                automobiles.forEach(a -> {
                    a.setJobs(List.of(job));
                });
                job.setAutomobiles(automobiles);
            }
        }

        List<Mechanic> mechanics = order.getMechanics()
                        .stream()
                        .map(m-> mechanicService.getMechanicByNameAndSurnameAndPhoneNumber(m.getName(), m.getSurname(), m.getPhoneNumber()))
                        .collect(Collectors.toList());

        if (mechanics != null && !mechanics.isEmpty() && mechanics.get(0)==null) {
            order.getMechanics().forEach(mechanicService::create);
            mechanics = order.getMechanics();
        }

        order.setJobs(jobs);
        order.setMechanics(mechanics);

        System.err.println("Final order " + order);
        orderService.create(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable(name = "id")int id) {
        Order order = orderService.getOrderById(id);
        return order != null
                ? new ResponseEntity<>(order, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/orders")
    public ResponseEntity<?> getOrders() {
        List<Order> orderList = orderService.getOrders();

        return orderList != null && !orderList.isEmpty()
                ? new ResponseEntity<>(orderList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    //beginWeek=" + beginDateWeekStr + "&endWeek=" + endDateWeekStr + "&beginMonth=" + beginDateMonthStr + "&endMonth=" + endDateMonthStr

    @GetMapping("/plannedTasks/{beginWeek}&{endWeek}&{beginMonth}&{endMonth}")
    public ResponseEntity<?> getOrdersForDays(@PathVariable(name = "beginWeek") String startWeekDateStr, @PathVariable(name = "endWeek") String finishWeekDateStr,@PathVariable(name = "beginMonth") String startMonthDateStr, @PathVariable(name = "endMonth") String finishMonthDateStr) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        Date startWeekDate = formatter.parse(startWeekDateStr);
        Date finishWeekDate = formatter.parse(finishWeekDateStr);
        Date startMonthDate = formatter.parse(startMonthDateStr);
        Date finishMonthate = formatter.parse(finishMonthDateStr);

        List<Order> orders = orderService.getOrderByDate(startWeekDate, finishWeekDate,startMonthDate,finishMonthate);

        return orders!=null && !orders.isEmpty()
                ? new ResponseEntity<>(orders, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/filteredOrder")
    public ResponseEntity<?> getOrdersForDays(@RequestBody Filter filter) {

        List<Order> orders = orderService.getOrders();
        if (filter.getFullNameMechanic()!=null)
            orders = orders.stream()
                .filter(
                        o-> {
                            List<Mechanic> mech = o.getMechanics()
                                    .stream()
                                    .filter(
                                            m -> (m.getSurname() + " " + m.getName() + " " + m.getPatronymic()).equals(filter.getFullNameMechanic())
                                    ).collect(Collectors.toList());

                            return !mech.isEmpty();
                        }
                ).collect(Collectors.toList());
        if (filter.getFullNameClient()!=null)
            orders = orders.stream()
                    .filter(
                            o-> (o.getCustomer().getLastName() + " " + o.getCustomer().getFirstName() + " " + o.getCustomer().getPatronymic()).equals(filter.getFullNameClient())
                    ).collect(Collectors.toList());

        if (filter.getNumberOfBox()!=null)
            orders = orders.stream()
                    .filter(
                            o-> o.getBox().equals(filter.getNumberOfBox())
                    ).collect(Collectors.toList());
        if (filter.getLicensePlate()!=null)
            orders = orders.stream()
                    .filter(
                            o-> o.getCar().getLicensePlate().equals(filter.getLicensePlate())
                    ).collect(Collectors.toList());
        if (filter.getBeginDate()!=null)
            orders = orders.stream()
                    .filter(
                            o-> o.getStart_Task().after(filter.getBeginDate().getTime())

                    ).collect(Collectors.toList());
        if (filter.getEndDate()!=null)
            orders = orders.stream()
                    .filter(
                            o-> o.getStart_Task().before(filter.getEndDate().getTime())
                    ).collect(Collectors.toList());

        return orders!=null && !orders.isEmpty()
                ? new ResponseEntity<>(orders, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
