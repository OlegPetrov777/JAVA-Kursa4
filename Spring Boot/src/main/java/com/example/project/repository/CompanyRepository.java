package com.example.project.repository;

import com.example.project.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findByName(String name);
    Boolean existsByName(String name);
    Boolean existsById(Integer id);
}