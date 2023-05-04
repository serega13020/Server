package com.example.carmixserver.service;

import com.example.carmixserver.model.Automobile;
import com.example.carmixserver.repository.AutomobileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutomobileService {
    private final AutomobileRepository automobileRepository;

    public AutomobileService(AutomobileRepository automobileRepository) {
        this.automobileRepository = automobileRepository;
    }

    public void createAutomobile(Automobile auto) {
        automobileRepository.save(auto);
    }

    public Automobile getAutomobile(int id) {
        return automobileRepository.findById(id).get();
    }

    public List<Automobile> getAutomobiles() {
        return automobileRepository.findAll();
    }

    public List<Automobile> getAutomobilesByBrend(String brend) {
        return automobileRepository.getAutomobileByBrend(brend);
    }

    public List<Automobile> getAutomobilesByModel(String model) {
        return automobileRepository.getAutomobileByModel(model);
    }

    public List<Automobile> getAutomobilesBySeries(String series) {
        return automobileRepository.getAutomobileBySeries(series);
    }

    public Automobile getAutomobileByAll(Automobile automobile) {
        return automobileRepository.getAutomobileByAll(automobile.getBrend(),automobile.getModel(),automobile.getSeries());

    }
}
