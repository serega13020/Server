package com.example.carmixserver.repository;

import com.example.carmixserver.model.Automobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutomobileRepository extends JpaRepository<Automobile, Integer> {
    @Query("SELECT a from Automobile a where a.brend=?1")
    public List<Automobile> getAutomobileByBrend(String brend);

    @Query("SELECT a from Automobile a where a.model=?1")
    public List<Automobile> getAutomobileByModel(String model);

    @Query("SELECT a from Automobile a where a.series=?1")
    public List<Automobile> getAutomobileBySeries(String series);

    @Query("SELECT a from Automobile a where  a.brend=?1 and a.model=?2 and a.series=?3")
    public Automobile getAutomobileByAll(String brend, String model, String series);
}
