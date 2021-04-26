package com.example.project.repository;

import com.example.project.entity.Category;
import com.example.project.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository  extends JpaRepository<Category, Long> {
    List<Category> findByName(String name);

}