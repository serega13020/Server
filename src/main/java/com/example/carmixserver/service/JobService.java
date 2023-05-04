package com.example.carmixserver.service;

import com.example.carmixserver.model.Automobile;
import com.example.carmixserver.model.Job;
import com.example.carmixserver.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public void create(Job job) {
        jobRepository.save(job);
    }

    public Job getJobById(int id) {
        return jobRepository.findById(id).get();
    }
    public Job getJobByTitleAndPrice(String title, String price) {return jobRepository.getJobByTitleAndPrice(title, price);}

    public List<Job> getJobs() {
        return jobRepository.findAll();
    }
}
