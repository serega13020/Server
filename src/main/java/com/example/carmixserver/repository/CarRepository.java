package com.example.carmixserver.repository;

import com.example.carmixserver.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    @Query("SELECT c from Car c where c.vin=?1")
    Car getCarByVin(String vin);
}
