package com.example.carmixserver.service;

import com.example.carmixserver.model.Customer;
import com.example.carmixserver.model.Mechanic;
import com.example.carmixserver.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomer(int id) {
        return customerRepository.findById(id).get();
    }

    public Customer getCustomerByNameAndSurnameAndPhoneNumber(String name, String surname, String phoneNumber) {
        return customerRepository.getCustomerByNameAndSurnameAndPhoneNumber(name,surname,phoneNumber);
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
}
