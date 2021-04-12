package com.example.project.repository;

import com.example.project.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository  extends JpaRepository<Model, Long> {
}