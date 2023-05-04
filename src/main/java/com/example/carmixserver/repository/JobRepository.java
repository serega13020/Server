package com.example.carmixserver.repository;

import com.example.carmixserver.model.Automobile;
import com.example.carmixserver.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
    @Query("SELECT j FROM Job j where j.title=?1 and j.price=?2")
    public Job getJobByTitleAndPrice(String title, String price);
}
