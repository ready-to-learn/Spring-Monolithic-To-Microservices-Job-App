package com.monolithic.jobapp.job;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private List<Job> jobs = new ArrayList<>();
    private long nextId = 1L;

    @Override
    public List<Job> findAll(){
        return jobs;
    }

    @Override
    public String createJob(@RequestBody Job job){
        job.setId(nextId++);
        jobs.add(job);
        return "Job created";
    }

    @Override
    public Job findById(long id) {
        Iterator<Job> iterator = jobs.iterator();
        Job job = iterator.next();
        if(job.getId().equals(id)){
                return job;
            }
        return null;
    }

    @Override
    public boolean deleteById(long id) {
        Iterator<Job> iterator = jobs.iterator();
        while(iterator.hasNext()){
            Job job = iterator.next();
            if(job.getId().equals(id)){
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}
