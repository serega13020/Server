package com.example.carmixserver.service;

import com.example.carmixserver.model.Order;
import com.example.carmixserver.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void create(Order order) {
        orderRepository.save(order);
    }

    public Order getOrderById(int id) {
        return orderRepository.findById(id).get();
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrderByDate(Date startWeek, Date endWeek, Date startMonth, Date endMonth) { return orderRepository.getOrderByDate(startWeek, endWeek,startMonth,endMonth); }
}
