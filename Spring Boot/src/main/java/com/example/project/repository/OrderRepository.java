package com.example.project.repository;

import com.example.project.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Boolean existsByName(String name);
    Boolean existsById(Integer id);
}
