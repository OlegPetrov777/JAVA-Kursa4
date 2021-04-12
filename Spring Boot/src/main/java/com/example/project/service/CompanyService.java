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

    public void create(Company manufacturer){
        companyRepository.save(manufacturer);
    }

    public List<Company> findAll(){
        return companyRepository.findAll();
    }

    public Optional<Company> find(Long id){
        return companyRepository.findById(id);
    }

    public void delete(Long id){
        companyRepository.deleteById(id);
    }
}
