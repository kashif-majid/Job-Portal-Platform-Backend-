package com.example.frostbite.jobMicroService.job.mapper;

import com.example.frostbite.jobMicroService.job.Job;
import com.example.frostbite.jobMicroService.job.dto.JobDTO;
import com.example.frostbite.jobMicroService.job.external.Company;
import com.example.frostbite.jobMicroService.job.external.Review;

import java.util.List;

public class JobMapper {
    public static JobDTO mapToJobWithCompanyDto(Job job, Company company, List<Review> reviews){
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setCompany(company);
        jobDTO.setReviews(reviews);

        return jobDTO;

    }
}
