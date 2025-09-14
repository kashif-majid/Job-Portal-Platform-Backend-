package com.example.frostbite.companyMicroService.company;

import com.example.frostbite.companyMicroService.company.dto.ReviewMessage;

import java.util.List;

public interface CompanyService {
    List<Company> findAllCompanies();
    void createCompany(Company company);
    Company getCompanyById(Long id);
    boolean deleteCompany(Long id);
    boolean updateCompany(Long id, Company updatedCompany);
    public void updateCompanyRating(ReviewMessage reviewMessage);
}
