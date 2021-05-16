package com.example.project.service;

import com.example.project.entity.Company;
import com.example.project.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public void create(Company company){
        companyRepository.save(company);
    }


    public List<Company> findAll(){
        return companyRepository.findAll();
    }


    public Optional<Company> find(Long id){
        return companyRepository.findById(id);
    }


    public List<Company> findByName(String name){
        return companyRepository.findByName(name);
    }


    public List<Company> update(Company company) {
        var updatedCompany = companyRepository.findById(company.getId());

        if (updatedCompany.isPresent()) {
            var updatedCompany_ = updatedCompany.get();

            updatedCompany_.setName(company.getName() != null
                    ? company.getName() : updatedCompany_.getName());

            companyRepository.save(updatedCompany_);
        }
        return companyRepository.findAll();
    }


    public void delete(Long id){
        companyRepository.deleteById(id);
    }
}
