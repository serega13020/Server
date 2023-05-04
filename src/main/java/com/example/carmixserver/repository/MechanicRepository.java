package com.example.carmixserver.repository;

import com.example.carmixserver.model.Mechanic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MechanicRepository extends JpaRepository<Mechanic, Integer> {
    @Query("SELECT m FROM Mechanic m WHERE m.name=?1 and m.surname=?2 and m.phoneNumber=?3")
    Mechanic getMechanicByNameAndSurnameAndPhoneNumber(String name, String surname, String phoneNumber);
}
