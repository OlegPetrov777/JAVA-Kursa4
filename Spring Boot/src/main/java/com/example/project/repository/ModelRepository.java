package com.example.project.repository;

import com.example.project.entity.Company;
import com.example.project.entity.Model;
import org.dom4j.rule.Mode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepository  extends JpaRepository<Model, Long> {
    List<Model> findByName(String name);

}