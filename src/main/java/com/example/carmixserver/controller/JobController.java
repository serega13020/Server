package com.example.carmixserver.controller;

import com.example.carmixserver.model.Automobile;
import com.example.carmixserver.model.Job;
import com.example.carmixserver.service.AutomobileService;
import com.example.carmixserver.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class JobController {
    private final JobService jobService;
    private final AutomobileService automobileService;

    public JobController(JobService jobService, AutomobileService automobileService) {
        this.jobService = jobService;
        this.automobileService = automobileService;
    }

    @PostMapping("/job")
    public ResponseEntity<?> createJob(@RequestBody Job job) {
        System.err.println("Job: " + job);
        List<Automobile> automobiles = job
                .getAutomobiles()
                .stream()
                .map(automobileService::getAutomobileByAll)
                .collect(Collectors.toList());
        System.err.println("Size is " + automobiles.size());
        System.err.println("First is " + automobiles.get(0));
        if (automobiles!=null && automobiles.get(0) == null) {
            System.err.println("Inner object is " + job.getAutomobiles().get(0).getBrend());
            job.getAutomobiles().forEach(automobileService::createAutomobile);
            automobiles = job.getAutomobiles();
        }
        automobiles.forEach(a->{a.setJobs(List.of(job));});
        job.setAutomobiles(automobiles);
        jobService.create(job);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("job/{id}")
    public ResponseEntity<?> getJobById(@PathVariable(name = "id")int id) {
        Job job = jobService.getJobById(id);
        return job != null
                ? new ResponseEntity<>(job, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/jobs")
    public ResponseEntity<?> getJobs() {
        List<Job> jobList = jobService.getJobs();

        return jobList!=null && !jobList.isEmpty()
                ? new ResponseEntity<>(jobList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
