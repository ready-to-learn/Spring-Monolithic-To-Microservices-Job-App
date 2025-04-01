package com.monolithic.jobapp.job;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll(){
        return jobRepository.findAll();
    }

    @Override
    @Transactional
    public String createJob(Job job){
        try{
            jobRepository.save(job);
            return "Job created";
        }catch (Exception e){
            e.printStackTrace();
            return "Job could not be created";
        }
    }

    @Override
    public Job findById(long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteById(long id) {
        try {
            jobRepository.deleteById(id);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean updateJob(long id, Job updatedJob) {
       return jobRepository.findById(id)
               .map(
                       job -> {
                           job.setTitle(updatedJob.getTitle());
                           job.setDescription(updatedJob.getDescription());
                           job.setMinSalary(updatedJob.getMinSalary());
                           job.setMaxSalary(updatedJob.getMaxSalary());
                           job.setLocation(updatedJob.getLocation());
                           jobRepository.save(job);
                           return true;
                       }
               ).orElse(false);

    }

}
