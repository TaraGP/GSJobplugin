package com.Ivoyant.GSJobplugin.controller;

import org.Ivoyant.service.JobPlugin;
import org.Ivoyant.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    final Logger logger = LoggerFactory.getLogger(JobController.class);
    private final JobPlugin jobPlugin;

    public JobController(JobPlugin jobPlugin) {
        this.jobPlugin = jobPlugin;
    }

    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        try {
            if (job.getCronExpression() == null) {
                throw new IllegalArgumentException("cronExpression cannot be null");
            }
            jobPlugin.createJob(job);
            return ResponseEntity.status(HttpStatus.CREATED).body(job);
        } catch (Exception e) {
            logger.error("Error in creating job: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<Job> getJob(@PathVariable Long jobId) {
        try {
            Job job = jobPlugin.getJob(jobId);
            if (job != null) {
                return ResponseEntity.ok(job);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("error in get: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{jobId}")
    public ResponseEntity<Void> updateJob(@PathVariable Long jobId, @RequestBody Job job) {
        try {
            job.setId(jobId);
            jobPlugin.updateJob(job);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("error in put: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long jobId) {
        try {
            Job job = jobPlugin.getJob(jobId);
            if (job != null) {
                jobPlugin.deleteJob(jobId);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("error in delete: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        try {
            List<Job> jobs = jobPlugin.getAllJobs();
            return ResponseEntity.ok(jobs);
        } catch (Exception e) {
            logger.error("error in get all: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

