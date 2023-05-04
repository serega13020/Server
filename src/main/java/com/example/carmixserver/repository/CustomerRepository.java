package com.example.carmixserver.repository;

import com.example.carmixserver.model.Customer;
import com.example.carmixserver.model.Mechanic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("SELECT c FROM Customer c WHERE c.firstName=?1 and c.lastName=?2 and c.phoneNumber=?3")
    Customer getCustomerByNameAndSurnameAndPhoneNumber(String name, String surname, String phoneNumber);

}
