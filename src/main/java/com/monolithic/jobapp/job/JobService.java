package com.monolithic.jobapp.job;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobService {

     List<Job> findAll();
     String createJob(Job job);
     Job findById(long id);
     boolean deleteById(long id);
}
