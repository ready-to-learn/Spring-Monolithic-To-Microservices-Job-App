package com.monolithic.jobapp.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Optional<List<Company>> findAll() {
        return Optional.of(companyRepository.findAll());
    }

    @Override
    public String createCompany(Company company) {
        companyRepository.save(company);
        return "Company created";
    }

    @Override
    public boolean updateCompany(Long id, Company updatedCompany) {
        Optional<Company> company = companyRepository.findById(id);
        if(company.isPresent()){
            Company companyToupdate = company.get();
            companyToupdate.setName(updatedCompany.getName());
            companyToupdate.setDescription(updatedCompany.getDescription());
            companyToupdate.setJobs(updatedCompany.getJobs());
            companyRepository.save(companyToupdate);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCompany(Long id) {
        if(companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Company> findById(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        return company.isPresent() ? company : null;
    }
}
