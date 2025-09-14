package com.example.frostbite.jobMicroService.job.impl;


import com.example.frostbite.jobMicroService.job.Job;
import com.example.frostbite.jobMicroService.job.JobRepository;
import com.example.frostbite.jobMicroService.job.JobService;
import com.example.frostbite.jobMicroService.job.clients.CompanyClient;
import com.example.frostbite.jobMicroService.job.clients.ReviewClient;
import com.example.frostbite.jobMicroService.job.dto.JobDTO;
import com.example.frostbite.jobMicroService.job.external.Company;
import com.example.frostbite.jobMicroService.job.external.Review;
import com.example.frostbite.jobMicroService.job.mapper.JobMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    JobRepository jobRepository;
    @Autowired
    RestTemplate restTemplate;

    private CompanyClient companyClient;
    private ReviewClient reviewClient;
    int attempt = 0;

    public JobServiceImpl(JobRepository jobRepository,CompanyClient companyClient,ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.companyClient= companyClient;
        this.reviewClient = reviewClient;

    }

    @Override
    //@CircuitBreaker(name= "companyBreaker",fallbackMethod = "companyBreakerFallback")
    //@Retry(name= "companyBreaker",fallbackMethod = "companyBreakerFallback")
    @RateLimiter(name= "companyBreaker",fallbackMethod = "companyBreakerFallback")
    public List<JobDTO> findAll() {
        System.out.println("Attempt: "+ ++attempt);
        List<Job> jobs = jobRepository.findAll();
//        return jobs.stream().map(this::convertToDto).collect(Collectors.toList());

        return jobs.stream().map(this::convertToDto).toList();
    }

    public List<String> companyBreakerFallback(Exception e){
        List<String> list = new ArrayList<>();
        list.add("Dummy");
        return list;
    }

    private JobDTO convertToDto(Job job){

            Company company = companyClient.getCompany(job.getCompanyId());
            List<Review> reviews = reviewClient.getReviews(job.getCompanyId());


            JobDTO jobDTO = JobMapper.mapToJobWithCompanyDto(job,company,reviews);
        return jobDTO;
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public JobDTO getJobById(Long id) {
       Job job =  jobRepository.findById(id).orElse( null);
       return convertToDto(job);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean updateJobById(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if(jobOptional.isPresent()){
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            jobRepository.save(job);
            return true;

        }
        return false;
    }

}
