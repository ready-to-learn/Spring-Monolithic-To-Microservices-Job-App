package com.monolithic.jobapp.company;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public interface CompanyService {
    Optional<List<Company>>  findAll();
    String createCompany(Company company);
    boolean updateCompany(Long id,Company company);
    boolean deleteCompany(Long id);

    Optional<Company> findById(Long id);
}
