package com.example.carmixserver.service;

import com.example.carmixserver.model.Mechanic;
import com.example.carmixserver.repository.MechanicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MechanicService {
    private final MechanicRepository mechanicRepository;

    public MechanicService(MechanicRepository mechanicRepository) {
        this.mechanicRepository = mechanicRepository;
    }

    public void create(Mechanic mechanic) {
        mechanicRepository.save(mechanic);
    }

    public Mechanic getMechanic(int id) {
        return mechanicRepository.findById(id).get();
    }

    public Mechanic getMechanicByNameAndSurnameAndPhoneNumber(String name, String surname, String phoneNumber) {
        return mechanicRepository.getMechanicByNameAndSurnameAndPhoneNumber(name,surname,phoneNumber);
    }

    public List<Mechanic> getMechanics() {
        return mechanicRepository.findAll();
    }
}
