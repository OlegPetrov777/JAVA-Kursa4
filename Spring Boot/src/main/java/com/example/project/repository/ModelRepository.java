package com.example.project.repository;

import com.example.project.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Long> {
    List<Model> findByName(String name);
    Boolean existsByName(String name);
    Boolean existsById(Integer id);
}